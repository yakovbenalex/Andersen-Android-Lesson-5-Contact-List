package ru.opalevapps.andersenandroidlesson5contactlist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

private const val ARG_FIRST_NAME = "firstName"
private const val ARG_LAST_NAME = "lastName"
private const val ARG_PHONE = "phone"
private const val ARG_ID_RECORD = "idRecord"

private const val TAG = "FragmentContactDetails"

class FragmentContactDetails : Fragment() {
    private var firstName: String? = null
    private var lastName: String? = null
    private var phone: String? = null
    private var idRecord: Int? = null
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etPhone: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            firstName = it.getString(ARG_FIRST_NAME)
            lastName = it.getString(ARG_LAST_NAME)
            phone = it.getString(ARG_PHONE)
            idRecord = it.getInt(ARG_ID_RECORD)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_contact_details, container, false)

        etFirstName = root.findViewById<EditText>(R.id.etFirstName).also { it.setText(firstName) }
        etLastName = root.findViewById<EditText>(R.id.etLastName).also { it.setText(lastName) }
        etPhone = root.findViewById<EditText>(R.id.etPhone).also { it.setText(phone) }

        root.findViewById<Button>(R.id.btnSaveContactDetails).setOnClickListener {
            requireActivity().supportFragmentManager.setFragmentResult(
                FragmentContactList.REQUEST_KEY,
                Bundle().apply {
                    putString(DATA_FIRST_NAME, etFirstName.text.toString())
                    putString(DATA_LAST_NAME, etLastName.text.toString())
                    putString(DATA_PHONE, etPhone.text.toString())
                    putInt(DATA_ID_RECORD, idRecord!!)
                })
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Inflate the layout for this fragment
        return root
    }

    companion object {
        const val FRAGMENT_CONTACT_DETAILS = "FRAGMENT_CONTACT_DETAILS"

        const val DATA_FIRST_NAME = "firstName"
        const val DATA_LAST_NAME = "lastName"
        const val DATA_PHONE = "phone"
        const val DATA_ID_RECORD = "idRecord"

        @JvmStatic
        fun newInstance(firstName: String, lastName: String, phone: String, idRecord: Int) =
            FragmentContactDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_FIRST_NAME, firstName)
                    putString(ARG_LAST_NAME, lastName)
                    putString(ARG_PHONE, phone)
                    putInt(ARG_ID_RECORD, idRecord)
                }
            }
    }
}