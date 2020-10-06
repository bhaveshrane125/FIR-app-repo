package com.example.sample_pbl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var full_name:EditText
    lateinit var doc_id:EditText
    lateinit var email_ad:EditText
    lateinit var phone_no:EditText
    lateinit var adress:EditText
    lateinit var submit:Button
    lateinit var article_type:EditText
    lateinit var types:Spinner
    lateinit var regions:Spinner
    lateinit var region_selected:String
    lateinit var type_selected:String
    lateinit var place_lf:EditText
    lateinit var item_description:EditText
    lateinit var date_time:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        full_name=findViewById(R.id.et_fullName)
        doc_id=findViewById(R.id.et_document_id)
        email_ad=findViewById(R.id.et_email)
        phone_no=findViewById(R.id.et_phone)
        adress=findViewById(R.id.et_address)
        submit=findViewById(R.id.bt_submit)
        article_type=findViewById(R.id.et_article_type)
        types=findViewById(R.id.sp_report_type)
        regions=findViewById(R.id.sp_region)
        place_lf=findViewById(R.id.et_place)
        item_description=findViewById(R.id.et_description)
        date_time=findViewById(R.id.et_date_time)

        val report_type=resources.getStringArray(R.array.Types)
        val region_list=resources.getStringArray(R.array.Regions)

        val adapter1=ArrayAdapter(this,android.R.layout.simple_spinner_item,report_type)
        types.adapter=adapter1

        types.onItemSelectedListener=object :
        AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                type_selected=report_type[position]
            }
        }


        val adapter2=ArrayAdapter(this,android.R.layout.simple_spinner_item,region_list)
        regions.adapter=adapter2

        regions.onItemSelectedListener=object :
        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                region_selected=region_list[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }





        submit.setOnClickListener {

            saveInfo()
        }
    }

    private fun saveInfo() {
        val namex=full_name.text.trim()
        val doc=doc_id.text.trim()
        val emailad=email_ad.text.trim()
        val phonex=phone_no.text.trim()
        val addressx=adress.text.trim()
        val article=article_type.text.trim()
        val region=region_selected
        val type_s=type_selected
        val place_action=place_lf.text.trim()
        val description=item_description.text.trim()
        val time_date=date_time.text.trim()

        if (namex.isEmpty() || doc.isEmpty()||emailad.isEmpty()||phonex.isEmpty()||addressx.isEmpty() || article.isEmpty() || place_action.isEmpty() || description.isEmpty() || time_date.isEmpty()){
            Toast.makeText(this,"Fill all details",Toast.LENGTH_LONG).show()
            return
        }

        val ref=FirebaseDatabase.getInstance().getReference("Report")

        val reportid=ref.push().key

        val save_report=SaveReport(reportid.toString(),namex.toString(),doc.toString(),emailad.toString(),phonex.toString(),addressx.toString(),article.toString(),region,time_date.toString(),type_s,description.toString(),place_action.toString())

        ref.child(reportid.toString()).setValue(save_report).addOnCompleteListener {
            Toast.makeText(this,"Succesfully saved",Toast.LENGTH_LONG).show()
            val intent_signout=Intent(this,SignoutActivity::class.java)
            startActivity(intent_signout)
            finish()


        }



    }
}
