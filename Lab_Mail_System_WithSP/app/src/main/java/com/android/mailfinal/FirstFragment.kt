package com.android.mailfinal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_mail.*

class FirstFragment(): Fragment() {

    private var messageAdapter: Adapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_mail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val messages:MutableList<Message> = mutableListOf()
        messages.add(Message("Pass your assignment", "Mr.Kevin", "Today is deadline by 23:59"))
        messages.add(Message("Netflix shows", "Netflix Originals", "See new series today!"))
        messages.add(Message("Fill up your resume", "Canva", "Complete your resume and publish in"))
        messages.add(Message("Registration completion", "LinkedIn", "Finish your registration"))
        messages.add(Message("Indeed Internships", "Indeed.com", "New internships form Motorola LLC"))
        messages.add(Message("Private message", "Ivan Ivanov", "Go to cinema today"))
        messages.add(Message("LeetCode exercises", "LeetCode", "C++ Hard Level exercises is waiting for you"))
        messages.add(Message("Coronavirus alert", "Minzdrav", "Wash your hands"))

        messageAdapter = Adapter(
            messageClickListener = {
                this.goToMailDetails(it)
            }
        )
        val manager = LinearLayoutManager(context)
        messageRecyclerView.apply {
            layoutManager = manager
            adapter = messageAdapter
        }
        messageAdapter?.addItems(messages)


    }

    private fun goToMailDetails(text:String){
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.right, MailDetailFragment.create(text))
            ?.apply { addToBackStack(this::class.java.simpleName) }
            ?.commit()
    }


}