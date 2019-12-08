package com.example.exercise1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonCalculate).setOnClickListener(){
            showPaymentDetail(it)
        }

        findViewById<Button>(R.id.buttonReset).setOnClickListener(){
            resetAll(it)
        }
    }

    private fun showPaymentDetail(view:View){
        val editCarPrice = findViewById<EditText>(R.id.editTextCarPrice)
        val editDownPayment = findViewById<EditText>(R.id.editTextDownPayment)
        val editLoanPeriod = findViewById<EditText>(R.id.editTextLoanPeriod)
        val editInterestRate = findViewById<EditText>(R.id.editTextInterestRate)
        val viewLoan = findViewById<TextView>(R.id.textViewLoan)
        val viewInterest = findViewById<TextView>(R.id.textViewInterest)
        val viewMonthlyRepay = findViewById<TextView>(R.id.textViewMonthlyRepayment)

        val carLoan = editCarPrice.text.toString().toInt() - editDownPayment.text.toString().toInt()
        val interest = carLoan * editInterestRate.text.toString().toDouble() / 100 * editLoanPeriod.text.toString().toInt()
        val monthlyRepay = (carLoan + interest) / editLoanPeriod.text.toString().toInt() / 12

        val number = java.lang.Double.valueOf(monthlyRepay)
        val dec = DecimalFormat("#.##")
        val r = dec.format(number)

        viewLoan.text = carLoan.toString()
        viewInterest.text =  interest.toString()
        viewMonthlyRepay.text = r

        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun resetAll(view:View){
        val editCarPrice = findViewById<EditText>(R.id.editTextCarPrice)
        val editDownPayment = findViewById<EditText>(R.id.editTextDownPayment)
        val editLoanPeriod = findViewById<EditText>(R.id.editTextLoanPeriod)
        val editInterestRate = findViewById<EditText>(R.id.editTextInterestRate)
        val viewLoan = findViewById<TextView>(R.id.textViewLoan)
        val viewInterest = findViewById<TextView>(R.id.textViewInterest)
        val viewMonthlyRepay = findViewById<TextView>(R.id.textViewMonthlyRepayment)

        editCarPrice.getText().clear()
        editDownPayment.getText().clear()
        editLoanPeriod.getText().clear()
        editInterestRate.getText().clear()
        viewLoan.setText(R.string.loan)
        viewInterest.setText(R.string.interest)
        viewMonthlyRepay.setText(R.string.monthly_repayment)

        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
