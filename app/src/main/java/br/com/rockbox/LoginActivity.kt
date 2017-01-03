package br.com.rockbox

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks

import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask

import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import java.util.ArrayList

import android.Manifest.permission.READ_CONTACTS
import android.content.*
import android.util.Log
import br.com.rockbox.dao.UserDAO
import br.com.rockbox.model.User
import br.com.rockbox.utils.GlobalConstants
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(){
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    var mAuthTask: UserLoginTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton.setOnClickListener { attemptLogin() }
    }



    private fun attemptLogin() {
        Log.i(GlobalConstants.LOGIN_ACTIVITY_TAG, "AttemptLogin")
        val username = login_username.text.toString()

        var focusView: View? = null
        var cancel:Boolean = false

        if (TextUtils.isEmpty(username)) {
            focusView = login_username
            cancel = true
        }

        if (cancel) {
            focusView!!.requestFocus()
        } else {
            showProgress(true)
            mAuthTask = UserLoginTask(username)
            mAuthTask!!.execute()
        }
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

            login_form!!.visibility = if (show) View.GONE else View.VISIBLE
            login_form!!.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    login_form!!.visibility = if (show) View.GONE else View.VISIBLE
                }
            })

            login_progress!!.visibility = if (show) View.VISIBLE else View.GONE
            login_progress!!.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    login_progress!!.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress!!.visibility = if (show) View.VISIBLE else View.GONE
            login_form!!.visibility = if (show) View.GONE else View.VISIBLE
        }
    }


    inner class UserLoginTask internal constructor(private val username: String) : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            // TODO: attempt authentication against a network service.
            Log.i(GlobalConstants.LOGIN_ACTIVITY_TAG, "UserLoginTask")

                //Chamada ao Realm
                val context:Context = this@LoginActivity
                Realm.init(context)
                val realm = Realm.getDefaultInstance()

                val newuser  = User(0, username)
                val udao = UserDAO (newuser, context)

                udao.insertUser(realm)

                Log.i(GlobalConstants.LOGIN_ACTIVITY_TAG, "Nome inserido ${udao.returnUser(realm).name}")

                val sharedPreferencesEditor :SharedPreferences.Editor = getSharedPreferences(GlobalConstants.PREFERENCES_TAG, Context.MODE_PRIVATE).edit()
                sharedPreferencesEditor.putBoolean(GlobalConstants.FIRST_TIME, false)
                sharedPreferencesEditor.putString(GlobalConstants.USERNAME, username)
                sharedPreferencesEditor.commit()

                val intent : Intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)

            return true
        }

        override fun onPostExecute(success: Boolean?) {
            mAuthTask = null
            showProgress(false)

            if (success!!) {
                finish()
            } else {
            }
        }

        override fun onCancelled() {
            mAuthTask = null
            showProgress(false)
        }
    }

}

