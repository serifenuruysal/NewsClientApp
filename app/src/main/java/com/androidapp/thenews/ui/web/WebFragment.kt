package com.androidapp.thenews.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.DialogFragment
import com.androidapp.thenews.R

/**
 * Created by S.Nur Uysal on 2019-11-04.
 */

class WebFragmentDialog : DialogFragment() {
    private var contentUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentUrl = arguments?.getString("contentUrl")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_web_view, container, false)
        val viewWeb: WebView = view.findViewById(R.id.view_web)
        viewWeb.loadUrl(contentUrl)
        return view
    }


    companion object {
        fun newInstance(contentUrl: String?): WebFragmentDialog {
            val f = WebFragmentDialog()

            // Supply num input as an argument.
            val args = Bundle()
            args.putString("contentUrl", contentUrl)
            f.arguments = args

            return f
        }
    }

}