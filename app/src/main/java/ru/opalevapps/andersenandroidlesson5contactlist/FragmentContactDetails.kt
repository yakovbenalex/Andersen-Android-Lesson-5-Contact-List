package ru.opalevapps.andersenandroidlesson5contactlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

private const val ARG_FIRST_NAME = "firstName"
private const val ARG_LAST_NAME = "lastName"
private const val ARG_PHONE = "phone"

class FragmentContactDetails : Fragment() {
    private var firstName: String? = null
    private var lastName: String? = null
    private var phone: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            firstName = it.getString(ARG_FIRST_NAME)
            lastName = it.getString(ARG_LAST_NAME)
            phone = it.getString(ARG_PHONE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_contact_details, container, false)
        root.findViewById<TextView>(R.id.etFirstName).text = firstName
        root.findViewById<TextView>(R.id.etLastName).text = lastName
        root.findViewById<TextView>(R.id.etPhone).text = phone

        // Inflate the layout for this fragment
        return root
    }

    companion object {
        const val FRAGMENT_CONTACT_DETAILS = "FRAGMENT_CONTACT_DETAILS"

        @JvmStatic
        fun newInstance(firstName: String, lastName: String, phone: String) =
            FragmentContactDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_FIRST_NAME, firstName)
                    putString(ARG_LAST_NAME, lastName)
                    putString(ARG_PHONE, phone)
                }
            }
    }
}