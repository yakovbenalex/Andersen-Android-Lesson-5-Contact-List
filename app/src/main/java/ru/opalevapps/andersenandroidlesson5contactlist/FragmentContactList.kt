package ru.opalevapps.andersenandroidlesson5contactlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment

class FragmentContactList : Fragment() {
    lateinit var lvContactList: ListView
    var contactArrayList: ArrayList<Contact> = ArrayList()
    var contactListAdapter: ContactListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeat(5) {
            contactArrayList.add(Contact())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_contact, container, false)

        lvContactList = root.findViewById(R.id.lvContactList)
        contactListAdapter = ContactListAdapter(root.context, contactArrayList)
        lvContactList.adapter = contactListAdapter

        // listView on item click listener
        lvContactList.setOnItemClickListener { adapterView, view, position, id ->
            val element = adapterView.getItemAtPosition(position) as Contact

            requireActivity().supportFragmentManager.apply {
                if (findFragmentByTag(FragmentContactDetails.FRAGMENT_CONTACT_DETAILS) == null) {
                    beginTransaction().run {
                        replace(
                            R.id.fragment_container,
                            FragmentContactDetails.newInstance(
                                element.firstName,
                                element.lastName,
                                element.phone
                            ),
                            FragmentContactDetails.FRAGMENT_CONTACT_DETAILS
                        )
                        addToBackStack(FragmentContactDetails.FRAGMENT_CONTACT_DETAILS)
                        commit()
                    }
                }
            }
        }

        // Inflate the layout for this fragment
        return root
    }

    companion object {
        const val FRAGMENT_CONTACT_LIST = "FRAGMENT_CONTACT_LIST"
        const val REQUEST_KEY = "FRAGMENT_CONTACT_LIST_REQUEST_KEY"

        @JvmStatic
        fun newInstance() = FragmentContactList()
    }
}