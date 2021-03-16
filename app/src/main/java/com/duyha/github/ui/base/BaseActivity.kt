package com.duyha.github.ui.base

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onStart() {
        super.onStart()
        setUp()
        setObservers()
        callViewModel()
    }

    protected abstract fun setUp()

    protected abstract fun callViewModel()

    protected abstract fun setObservers()

    protected abstract fun createViewModel(): VM

    private fun initViewModel() {
        viewModel = createViewModel()
        viewModel.msg.observe(this, {
            it.getContentIfNotHandled()?.let { msgId ->
                showMessage(msgId)
            }
        })
    }

    fun showMessage(msgId: Int, okAction: () -> Unit = {}) {
        showMessage(getString(msgId), okAction)
    }

    private fun showMessage(msg: String, okAction: () -> Unit) {
        val content = findViewById<View>(android.R.id.content)
        Snackbar.make(content, msg, Snackbar.LENGTH_INDEFINITE)
            .show()
    }

    fun requestPermission(
        permission: String,
        requestCode: Int
    ): Boolean {
        val isGranted =
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        if (!isGranted) {
            ActivityCompat.requestPermissions(
                this, arrayOf(permission),
                requestCode
            )
        }
        return isGranted
    }

    fun hideKeyboard() {
        val inputMethodManager: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.let { view ->
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun <T> navigateToScreen(activity: Class<T>, finish: Boolean = true) {
        val intent = Intent(this, activity)
        startActivity(intent)
        if (finish)
            finish()
    }
}