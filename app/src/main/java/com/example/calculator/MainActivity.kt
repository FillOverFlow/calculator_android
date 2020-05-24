package com.example.calculator



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import android.widget.Button
import android.widget.EditText

import java.lang.Exception

import java.util.*



class MainActivity : AppCompatActivity() {


    private var  text1:EditText? = null
    private var  text2:EditText? = null
    private var  btn7:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text1 = findViewById(R.id.EditText01)
        text2 = findViewById(R.id.EditText02)
        btn7  = findViewById(R.id.ButtonNumber07)






        findViewById<Button>(R.id.ButtonNumber01).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonNumber02).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonNumber03).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonNumber04).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonNumber05).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonNumber06).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonNumber07).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonNumber08).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonNumber09).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonNumber00).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonSymbolDot).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonSymbolClear).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonSymbolDel).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonSymbolAdd).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonSymbolSubtract).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonSymbolDivide).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonSymbolMultiply).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonSymbolBracketRight).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonSymbolBracketLeft).setOnClickListener {
            handleClickButton(it)
        }
        findViewById<Button>(R.id.ButtonSymbolExe).setOnClickListener {
            val strStack:String  = toPostFix(text1?.text.toString())

            val intValue:Float  = calculator(strStack)
            text2?.setText(intValue.toString())
            testCall(it,intValue.toString())
        }




    }

    private  fun testCall(view:View, result:String){
        val snack = Snackbar.make(view,"Call result : $result",Snackbar.LENGTH_SHORT)
        snack.show()


    }

    private fun handleClickButton(view: View){
        when(view.id){
            R.id.ButtonNumber00 -> text1?.append("0")
            R.id.ButtonNumber01 -> text1?.append("1")
            R.id.ButtonNumber02 -> text1?.append("2")
            R.id.ButtonNumber03 -> text1?.append("3")
            R.id.ButtonNumber04 -> text1?.append("4")
            R.id.ButtonNumber05 -> text1?.append("5")
            R.id.ButtonNumber06 -> text1?.append("6")
            R.id.ButtonNumber07 -> text1?.append("7")
            R.id.ButtonNumber08 -> text1?.append("8")
            R.id.ButtonNumber09 -> text1?.append("9")
            R.id.ButtonSymbolDot -> text1?.append(".")
            R.id.ButtonSymbolBracketLeft -> text1?.append("(")
            R.id.ButtonSymbolBracketRight -> text1?.append(")")
            R.id.ButtonSymbolMultiply -> text1?.append("*")
            R.id.ButtonSymbolDivide -> text1?.append("/")
            R.id.ButtonSymbolSubtract -> text1?.append("-")
            R.id.ButtonSymbolAdd -> text1?.append("+")
            R.id.ButtonSymbolDel ->{
                if(text1?.text.toString().length > 0){
                    val stringTextTrim :String = text1?.text.toString().substring(0,text1?.text.toString().length -1)
                    text1?.setText("")
                    text1?.append(stringTextTrim)
                }
            }
            R.id.ButtonSymbolClear -> {text1?.setText(""); text2?.setText("")}
            R.id.ButtonSymbolExe -> {
                //convert to PostFix
                val strStack:String  = toPostFix(text1?.text.toString())

                //val intValue:Float = calculator(strStack)

                //setText 2
                //text2?.setText(intValue.toString())

            }


        }
    }

    private fun delAction(text1: EditText){
        if(text1.text.toString().length > 0){
            val stringTextTrim :String = text1.text.toString().substring(0,text1.text.toString().length -1)
            text1.setText("")
            text1.append(stringTextTrim)

        }
    }

    //check priority options
    private fun getPriority(charOparetor: Char):Int{
        if(charOparetor == '+' || charOparetor == '-'){
            return 1;
        }else if(charOparetor == '*' || charOparetor == '/'){
            return 2;
        }
        return 0;
    }

    //check is Float?
    private fun isFloat(strInput: String):Boolean{
        try {
            strInput.toFloat()
            return true;
        }catch (e :Exception){
            return false;
        }
    }

    private  fun toPostFix(strInfix:String): String {
        var strExpression = ""
        var strPostFix = ""

        val strInfix2 = strInfix.replace("\\+|\\(|\\)|-|\\*|/".toRegex(), " $0 ")
        val strTokenizer = StringTokenizer(strInfix2)

        val  operatorStack =  Stack<Char>();

        while (strTokenizer.hasMoreTokens()) {
            strExpression = strTokenizer.nextToken()

            if (Character.isDigit(strExpression[0])) {
                strPostFix = strPostFix + " " + java.lang.Float.parseFloat(strExpression)
            } else if (strExpression == "(") {
                val operator = '('
                operatorStack.push(operator)
            } else if (strExpression == ")") {
                while ((operatorStack.peek() as Char).toChar() != '(') {
                    strPostFix = strPostFix + " " + operatorStack.pop()
                }

                operatorStack.pop()
            } else {

                while (!operatorStack.isEmpty() && operatorStack.peek() != '('  && getPriority(
                        strExpression[0]
                    ) <= getPriority((operatorStack.peek() as Char).toChar())
                ) {
                    strPostFix = strPostFix + " " + operatorStack.pop()
                }

                val operator = strExpression[0]
                operatorStack.push(operator)
            }
        }
        while (!operatorStack.isEmpty()){
            strPostFix = strPostFix + " "+operatorStack.pop()
        }

        return strPostFix

    }

    //calculator stack
    private fun calculator(strPostfix:String):Float{
        var a:Float
        var b:Float
        var result:Float = .0F
        var i = 0

        val arrPostfix = strPostfix.split(" ")
        val calStack   = Stack<Float>()


        while (i < strPostfix.length){

            val ch:String = arrPostfix[i]
            if(isFloat(ch)){
                calStack.push(ch.toFloat())

            }else{
                if(ch.equals("+")){
                    a = calStack.pop()
                    b = calStack.pop()
                    result = a + b
                    calStack.push(result)

                    return result

                }else if(ch.equals("-")){
                    a = calStack.pop()
                    b = calStack.pop()
                    result = a - b
                    calStack.push(result)
                    return result
                }else if(ch.equals("*")){
                    a = calStack.pop()
                    b = calStack.pop()
                    result = a * b
                    calStack.push(result)
                    return result
                }else if(ch.equals("/")){
                    a = calStack.pop()
                    b = calStack.pop()
                    result = b / a
                    calStack.push(result)
                    return result
                }else if(ch.equals(" ")){
                   print("test")
                }
            }

            i++

        }
        return result
    }






}

