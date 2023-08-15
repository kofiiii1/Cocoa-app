package com.example.cocofa
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Upload : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private val pickImage = 100
    private var imageUri: String? = null
    private lateinit var dbHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        dbHelper = MyDatabaseHelper(this)
        imageView = findViewById(R.id.post_image)

        val galleryBtn: Button = findViewById(R.id.gallery_button)
        galleryBtn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        val postBtn: Button = findViewById(R.id.upload_button)
        postBtn.setOnClickListener {
            createPost()
        }
    }

    private fun createPost() {
        val postImage = (findViewById<ImageView>(R.id.post_image).drawable as BitmapDrawable).bitmap
        val postDescription = findViewById<EditText>(R.id.post_description).text.toString()

        val post = Post(
            image = dbHelper.getImageBytes(postImage),
            description = postDescription
        )

        dbHelper.addPost(post)
        finish()
        Toast.makeText(this,"Post created successfully", Toast.LENGTH_LONG).show()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data.toString()
            imageView.setImageURI(data?.data)
        }
    }
}

data class Post(
    var id: Int = 0,
    var image: ByteArray? = null,
    var description: String? = null
)

