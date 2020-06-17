package com.github.lilei.coroutinepermissionsdemo

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.lilei.coroutinepermissions.InlineRequestPermissionException
import com.github.lilei.coroutinepermissions.requestPermissionsForResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val permsSd = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)

    private val permsPic = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onClick()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                requestPermissionsForResult(*permsSd, rationale = "为了更好的提供服务，需要获取存储空间权限")
            } catch (e: Exception) {
            }
        }
    }

    private fun onClick() {
        sdBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    requestPermissionsForResult(*permsSd, rationale = "为了更好的提供服务，需要获取存储空间权限")
                    startActivity(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))
                } catch (e: InlineRequestPermissionException) {
                    Toast.makeText(this@MainActivity, "失败", Toast.LENGTH_SHORT).show()
                }
            }
        }

        picBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    requestPermissionsForResult(*permsPic, rationale = "为了更好的提供服务，需要获取拍照权限")
                    startActivity(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                } catch (e: InlineRequestPermissionException) {
                    Toast.makeText(this@MainActivity, "失败", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
