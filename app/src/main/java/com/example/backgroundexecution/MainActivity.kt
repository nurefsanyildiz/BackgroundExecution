package com.example.backgroundexecution

import android.app.Dialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        btn_execute.setOnClickListener { view ->
            ExecuteAsyncTask("Background task executed successfully.").execute()
        }
    }

    private inner class ExecuteAsyncTask(val value: String) : AsyncTask<Any, Void, String>() {

        var customProgressDialog: Dialog? = null

        override fun onPreExecute() {
            super.onPreExecute()
            showProgressDialog()
        }

        override fun doInBackground(vararg params: Any): String {
            for (i in 1..10000) {
                Log.e("i : ", "" + i)
            }
            return value
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)

            cancelProgressDialog()

            Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
        }

        private fun showProgressDialog() {
            customProgressDialog = Dialog(this@MainActivity)
            customProgressDialog!!.setContentView(R.layout.dialog_custom_progress)
            customProgressDialog!!.show()
        }

        private fun cancelProgressDialog() {
            if (customProgressDialog != null) {
                customProgressDialog!!.dismiss()
                customProgressDialog = null
            }
        }
    }

}