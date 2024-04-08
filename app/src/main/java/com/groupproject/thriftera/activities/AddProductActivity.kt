package com.groupproject.thriftera.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.groupproject.thriftera.R
import com.groupproject.thriftera.constants.PRODUCTS_COLLECTION
import com.groupproject.thriftera.data.Product
import com.groupproject.thriftera.databinding.ActivityAddProductBinding
import com.groupproject.thriftera.notifications.NotificationHelper
import com.groupproject.thriftera.util.randomIdGenerator
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@AndroidEntryPoint
class AddProductActivity : AppCompatActivity() {

    @Inject
    lateinit var notificationHelper: NotificationHelper

    private val binding by lazy { ActivityAddProductBinding.inflate(layoutInflater) }
    val selectedColors = mutableListOf<Int>()
    var selectedImages = mutableListOf<Uri>()
    val firestore = Firebase.firestore


    private val storage = FirebaseStorage.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //shows the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        //4
        binding.buttonColorPicker.setOnClickListener {
            ColorPickerDialog
                .Builder(this)
                .setTitle("Product color")
                .setPositiveButton("Select", object : ColorEnvelopeListener {

                    override fun onColorSelected(envelope: ColorEnvelope?, fromUser: Boolean) {
                        envelope?.let {
                            selectedColors.add(it.color)
                            updateColors()
                        }
                    }

                }).setNegativeButton("Cancel") { colorPicker, _ ->
                    colorPicker.dismiss()
                }.show()
        }


        val selectImagesActivityResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data

                    //Multiple images selected
                    if (intent?.clipData != null) {
                        val count = intent.clipData?.itemCount ?: 0
                        (0 until count).forEach {
                            val imagesUri = intent.clipData?.getItemAt(it)?.uri
                            imagesUri?.let { selectedImages.add(it) }
                        }

                        //One images was selected
                    } else {
                        val imageUri = intent?.data
                        imageUri?.let { selectedImages.add(it) }
                    }
                    updateImages()
                }
            }
        //6
        binding.buttonImagesPicker.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.type = "image/*"
            selectImagesActivityResult.launch(intent)
        }

        invalidateOptionsMenu()
    }

    //on back pressed, close the activity
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.saveProduct) {
            val productValidation = validateInformation()
            if (!productValidation) {
                Toast.makeText(this, "Check your inputs", Toast.LENGTH_SHORT).show()
                return false
            }
            saveProducts() {
                Log.d("test", it.toString())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validateInformation(): Boolean {
        if (selectedImages.isEmpty())
            return false
        if (binding.edName.text.toString().trim().isEmpty())
            return false
        if (binding.edCategory.text.toString().trim().isEmpty())
            return false
        if (binding.edPrice.text.toString().trim().isEmpty())
            return false
        return true
    }

    //3
    private fun saveProducts(state: (Boolean) -> Unit) {
        val sizes = getSizesList(binding.edSizes.text.toString().trim())
        val imagesByteArrays = getImagesByteArrays() //7
        val name = binding.edName.text.toString().trim()
        val images = mutableListOf<String>()
        val category = binding.edCategory.text.toString().trim()
        val productDescription = binding.edDescription.text.toString().trim()
        val price = binding.edPrice.text.toString().trim()
        val offerPercentage = binding.edOfferPercentage.text.toString().trim()

        lifecycleScope.launch {
            val products = firestore.collection(PRODUCTS_COLLECTION).document()
            val productId = products.id
            showLoading()
            try {
                async {
                    imagesByteArrays.forEach {
                        launch {
                            val imagesStorage =
                                storage.reference.child("products/images/$productId")
                            val result = imagesStorage.putBytes(it).await()
                            val downloadUrl = result.storage.downloadUrl.await().toString()
                            images.add(downloadUrl)
                        }
                    }
                }.await()
            } catch (e: java.lang.Exception) {
                hideLoading()
                state(false)
            }
            val product = Product(
                productId,
                name,
                category,
                price.toFloat(),
                if (offerPercentage.isEmpty()) null else offerPercentage.toFloat(),
                productDescription.ifEmpty { null },
                selectedColors,
                sizes,
                images
            )
            products.set(product).addOnSuccessListener {
                state(true)
                hideLoading()
                clearFields()
                showSuccessToast()

                handleNotification()

            }.addOnFailureListener {
                state(false)
                hideLoading()
            }
        }
    }

    private fun handleNotification() {
        Handler(Looper.getMainLooper()).postDelayed({
            notificationHelper.postNotification(
                randomIdGenerator(),
                "Thrift Era",
                "There is a new listing in the app. Check it out! 😎"
            )
        }, 3500)
    }

    private fun showSuccessToast() {
        Toast.makeText(
            this@AddProductActivity,
            "Product added successfully",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun clearFields() {
        with(binding) {
            edName.text.clear()
            edCategory.text.clear()
            edDescription.text.clear()
            edPrice.text.clear()
            edOfferPercentage.text.clear()
            edSizes.text.clear()
            selectedColors.clear()
            selectedImages.clear()
            tvSelectedColors.text = ""
            tvSelectedImages.text = ""
        }
    }

    private fun hideLoading() {
        binding.progressbar.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        binding.progressbar.visibility = View.VISIBLE

    }

    private fun getImagesByteArrays(): List<ByteArray> {
        val imagesByteArray = mutableListOf<ByteArray>()
        selectedImages.forEach {
            val stream = ByteArrayOutputStream()
            val imageBmp = MediaStore.Images.Media.getBitmap(contentResolver, it)
            if (imageBmp.compress(Bitmap.CompressFormat.JPEG, 85, stream)) {
                val imageAsByteArray = stream.toByteArray()
                imagesByteArray.add(imageAsByteArray)
            }
        }
        return imagesByteArray
    }

    private fun getSizesList(sizes: String): List<String>? {
        if (sizes.isEmpty())
            return null
        val sizesList = sizes.split(",").map { it.trim() }
        return sizesList
    }

    //5
    private fun updateColors() {
        var colors = ""
        selectedColors.forEach {
            colors = "$colors ${Integer.toHexString(it)}, "
        }
        binding.tvSelectedColors.text = colors
    }

    private fun updateImages() {
        binding.tvSelectedImages.setText(selectedImages.size.toString())
    }
}