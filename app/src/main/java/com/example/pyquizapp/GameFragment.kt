package com.example.pyquizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pyquizapp.databinding.FragmentGameBinding
import kotlinx.android.synthetic.main.fragment_game.*


class GameFragment : Fragment() {
    data class Question(
        val text: String,
        val answers: List<String>,
        val type: String,
        val code: String
    )

    ///prvi odgovor je tacan odgovor na pitanje. Pitanja se randomizirju prije prikazivanja
    //pitanja. Sva pitanja ima 4 odgovor kao ponudjena.
    private val hardquestions: MutableList<Question> = mutableListOf(
        Question(text = "What is the output for",code = " \n 'you are doing well' [2:999]\n",
            answers = listOf(
                "u are doing well",
                " ",
                "Index error",
                "you are doing well"
            ),type = "choice"),
        Question(text = "Which is the special symbol used in python to add comments?",code = "",
            answers = listOf(
                "$",
                "//",
                "/*....*/",
                "#"
            ),type = "choice"),
        Question(text = " Mathematical operations can be performed on a string.", code = "",
            answers = listOf(
                "True",
                "False",
                "None of above",
                "True/False"
            ), type = "choice"),
        Question(text = "Which of the following data types is not supported in python ?" ,code = "",
            answers = listOf(
                "Slice",
                "String",
                "Number",
                "List"
            ),type = "choice"),
        Question(text = "Which one of the following has the highest precedence in the expression?",
            code = "",
            answers = listOf(
                "Parentheses",
                "Addition",
                "Multiplication",
                "Exponential"
            ), type = "choice"),
        Question(text = "Analyze the given below code?",
            code = "    \n class Demo: \n" +
                    "       def __init__(self,d): \n" +
                    "       self.d=d \n" +
                    "       def print(self): \n" +
                    "       print(d)  \n" +
                    "   a = Demo(''Hello'') \n" +
                    "   a.print() \n",
            answers = listOf(
                "Program will print ‘Hello’ if we change \n print(d) to print(self.d).",
                "You cannot use print(self) \n as a function name.",
                "Program has an error because class \n A does not have a constructor.",
                "Syntax Error."
            ),type = "choice"),
        Question(text="Find the output of the code?",
            code = "    \n def f(a, b = 1, c = 2): \n"+
                    "       print('a is: ',a, 'b is: ' , b, 'c is: ' , c) \n"+
                    "   f(2, c = 2) \n"+
                    "   f(c = 100, a = 110)\n",
            answers = listOf(
                "a is: 2 b is: 1 c is: 2\n" +
                        "\ta is: 110 b is: 1 c is: 100",
                "a is: 2 b is: 2 c is: 2\n"+
                        "\ta is: 110 b is: 2 c is: 100",
                "a is: 0 b is: 2 c is: 2\n" +
                        "\ta is: 110 b is: 0 c is: 100",
                "a is: 110 b is: 0 c is: 100\n" +
                        "\ta is: 110 b is: 0 c is: 100"
            ),type = "choice"),
        Question(text="Analyze the given below code?",
            code = "    \n class Demo: \n" +
                    "       def __init__(self,d): \n" +
                    "           self.d=d \n" +
                    "       def print(self): \n" +
                    "           print(d)  \n" +
                    "   a = Demo(''Hello'') \n" +
                    "   a.print() \n",
            answers = listOf(
                "Program will print ‘Hello’ if we change \n print(d) to print(self.d).",
                "You cannot use print(self) as a function name.",
                "Program has an error because class A \n does not have a constructor.",
                "Syntax Error."
            ),type = "choice"),
        Question(text = " What is the following function compares elements of both lists? ",
            code = "",
            answers = listOf(
                "cmplist1,list2",
                "lenlist1,list2",
                "maxlist1,list2",
                "minlist1,list2"
            ),type = "choice"
        ),
        Question(text = "Why do we learn Python?",
            code = "",
            answers = listOf(
                "It's simple and easy to learn",
                "It's a really big snake",
                "It's name of a very funny comedy show",
                "It's so rare that no one else knows it"
            ),type = "choice"),
        Question(text="How is memory managed in Python?",
            code = "",
            answers = listOf(
                "Memory is managed through private heaps.",
                "Private heap is managed by python memory manager.",
                "None of above",
                "Don't know"
            ),type = "multiple"),
        Question(text="What are the uses of List Comprehensions feature of Python?",
            code = "",
            answers = listOf(
                "List comprehensions help to create and manage \n" +
                        "lists in a simpler and clearer way than \n" +
                        "using map(),filter() and lambda.",
                "Each list comprehension consists of an expression " +
                        "\n followed by a clause,then zero or more " +
                        "\nfor or if clauses." ,
                "None of above",
                "x"),type = "multiple"),
        Question(text = "What is the optional statement used in a try except statement in Python?",
            code = "",
            answers = listOf(
                "Else clause: It is useful for code that must\n" +
                        " be executed when the try block \n"+
                        " does not create any exception",
                "Finally clause: It is useful for code that " +
                        "\n must be executed irrespective of " +
                        "\n whether an exception is generated or not.",
                "None of above",
                "There is no optional statement"
            ),type = "multiple"),
        Question(text = "When you need ordered container of things,\n" +
                "which will be manipulated,use lists?",
            code = "",
            answers = listOf(
                "Dictionary is key,value pair container \n" +
                        "and hence is not ordered.Use it when\n" +
                        " you need fast access to elements," +
                        "\n not in ordered fashion.",
                "Lists are indexed and index of the list cannot be string \n" +
                        "e.g.list ['myelement'] is not a " +
                        "\n valid statement in python.",
                "None of above",
                "I don't need that"
            ),type = "multiple")
    )
    private val mediumquestions: MutableList<Question> = mutableListOf(
        Question(text = " What is the output of",code = " \n len[1,2,3]\n ",
            answers = listOf(
                "3",
                "1",
                "2",
                "4"),type = "text"),
        Question(text = "What is the output of ",code = " \n [1, 2, 3] + [4, 5, 6] \n",
            answers = listOf(
                " [1, 2, 3, 4, 5, 6] ",
                "[1,2,3],[4,5,6]",
                "1, 2, 3, 4, 5, 6",
                "21"),type = "text"),
        Question(text = "What is the output of ",code = "\n 3 in [1, 2, 3] \n",
            answers = listOf(
                "true",
                "false",
                "Error",
                "None of above!"),type = "text"),

        Question(text = "What is the output for ",
            code = "\n    S = [['him', 'sell'], [90, 28, 43]]\n   S[0][1][1]\n",
            answers= listOf(
                "e",
                "i",
                "90",
                "h"
            ),type = "choice"),
        Question(text="Syntax error in python is detected by _________at _______.",code = "",
            answers = listOf(
                "interpreter/ run time",
                "compiler/ compile time",
                "compiler/ run time",
                "interpreter/ compile time"
            ),type = "choice"),
        Question(text = "Suppose we have two sets A & B, then A<B is:",code = "",
            answers = listOf(
                "True if A is a proper subset of B.",
                "True if len(A) is less than len(B).",
                "True if the elements in A when compared " +
                        "\nare less than the elements in B.",
                "True if A is a proper superset of B."
            ),type = "choice"),
        Question(text = "How do you insert COMMENTS in Python code?" ,code = "",
            answers = listOf(
                "#This is a comment",
                "/*This is a comment*/",
                "//This is a comment",
                "None of above"
            ),type = "choice"),
        Question(text = "String data type can be defined as " ,code= "",
            answers = listOf(
                "holds alpanumeric text",
                "holds whole numbers",
                "holds numbers with decimal point",
                "holds either a true or false"
            ),type = "choice"),
        Question(text = "Integer data type can be defined as ",code = "",
            answers = listOf(
                "holds whole numbers",
                "holds alpanumeric data as text",
                "holds numbers with decimal point",
                "holds either a true or false"
            ), type = "choice"),
        Question(text = "What is the code for a STRING in python?",code = "",
            answers = listOf(
                "str",
                "stg",
                "stri",
                "None of above"
            ),type = "text"),
        Question(text = "What is the output of the following loop",
            code = "    \nfor l in 'Jhon':\n" +
                    "       if l == 'o':\n" +
                    "           pass\n" +
                    "       print(l, end=\", \")\n",
            answers = listOf(
                "J,h,n",
                "J,h,o,n",
                "John",
                "Jhn"
            ),type = "choice"),
        Question(text = "What is the value of the var after the for loop completes its execution",
            code = "\n    var = 10\n" +
                    "   for i in range(10):\n" +
                    "       for j in range(2, 10, 1):\n" +
                    "           if var % 2 == 0:\n" +
                    "               continue\n" +
                    "               var += 1\n" +
                    "       var+=1\n" +
                    "   else:\n" +
                    "       var+=1\n" +
                    "   print(var)\n",
            answers = listOf(
                "20",
                "21",
                "10",
                "30"
            ),type = "choice"),
        Question(text = "Is python the right choice for Web based Programming?",
            code = "",
            answers = listOf(
                "Yes",
                "Depends",
                "No",
                "None of above"
            ),type = "multiple"),
        Question(text="When do you use list vs.tuple vs.dictionary vs.set?",
            code = "",
            answers = listOf(
                "List and Tuple are both ordered containers.",
                "If you want an ordered container of constant \n" +
                        "elements use tuple as tuples are immutable objects.",
                "Never",
                "None of above"
            ),type = "multiple")
    )
    private val easyquestions: MutableList<Question> = mutableListOf(
        Question(text = "Which of the following is correct about Python?",code = "",
            answers = listOf(
                "All of the above.",
                "Python is a high-level, interpreted, interactive and \n " +
                        "object-oriented scripting language. ",
                "Python is designed to be highly readable.",
                "It uses English keywords frequently where as other \n" +
                        "languages use punctuation, and it has fewer \n" +
                        "syntactical constructions than other languages."),
            type = "choice"),
        Question(text =  "Which of the following data types is not supported in python? ",code = "",
            answers = listOf(
                "Slice",
                "Numbers",
                "Strings",
                "List"),type = "choice"),
        Question(text = "What is the output of",code = "\n print str if str = 'Hello World!' \n",
            answers = listOf(
                "Hello World!",
                "Error",
                "str",
                "None of above"),type = "choice"),
        Question(text = "What is the output of ",code = "\n print str[0] if str = 'Hello World!'\n",
            answers = listOf(
                "H",
                "Hello World!",
                "ello World!",
                "None of above."),type = "choice"),
        Question(text = "What is the output of ",code = "\n print str[2:5] if str = 'Hello World!' \n",
            answers = listOf(
                "llo",
                "llo World!",
                "H",
                "None of above."),type = "choice"),
        Question("What is the output of",code = "\n print str * 2 if str = 'Hello World!'\n",
            answers = listOf(
                "Hello World!Hello World! ",
                "Hello World! * 2 ",
                "Hello World",
                "None of above. "),type = "choice"),
        Question(text = "What is the output of ",
            code = "\n    print list[2:] if list = [ 'abcd', 786 , 2.23, 'john', 70.2 ] \n",
            answers = listOf(
                "[2.23, 'john', 70.2]",
                "['abcd',786,2.23,'john',70.2]",
                "abcd",
                "[786,2.23]"),type = "choice"),
        Question(text = "Which of the following operator in python performs exponential power calculation on operands? ",
            code = "",
            answers = listOf(
                " ** ",
                " // ",
                " is ",
                "not in"),type = "choice"),
        Question(text = "Which of the following operator in python evaluates to true if the variables on either side of the operator point to the same object and false otherwise? ",
            code = "",
            answers = listOf(
                "is",
                "**",
                "//",
                "not in"),type = "choice"),
        Question(text = "Which of the following operator in python performs the division on operands where the result is the quotient in which the digits after the decimal point are removed? ",
            code = "",
            answers = listOf(
                "//",
                "**",
                "is",
                "not in"),type = "choice"),
        Question(text = " Which of the following statement terminates the loop statement and transfers execution to the statement immediately following the loop? ",
            code = "",
            answers = listOf(
                "break",
                "continue",
                "pass",
                "None of above"),type = "choice"),
        Question(text = "Which of the following function capitalizes first letter of string? ",
            code = "",
            answers = listOf(
                "capitalize",
                "shuffleLst",
                "isalnum",
                "isdigit"),type = "choice"),
        Question(text = " Which of the following function checks in a string that all characters are digits? ",
            code= "",
            answers = listOf(
                "isnumeric",
                "islower",
                "isspace",
                "istitle"),type = "choice")
    )
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private var numQuestions = 0
    private var difficulty = 0
    private var correctAnswers = 0
    private var joker = false
    private var emh = ""
    private var checkedAnswers : MutableList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false)
        setMenuVisibility(false)
        val args = GameFragmentArgs.fromBundle(requireArguments())
        numQuestions = args.numOfQuestions
        difficulty = args.levelD
        if(args.levelD == 1){
            emh = getString(R.string.easy)
        }
        if(args.levelD == 2){
            emh = getString(R.string.medium)
        }
        if(args.levelD == 3){
            emh = getString(R.string.hard)
        }
        Toast.makeText(context, "Number of questions: ${args.numOfQuestions}, Difficulty: ${emh}", Toast.LENGTH_LONG).show()

        //izmijesa pitanja
        randomizeQuestions()

        //bind fragment klasu na layout
        binding.game=this

        // listener zasubmitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            firstAnswerCheck.setOnClickListener{
                checkedAnswers.add(firstAnswerCheck.text.toString())
            }
            secondAnswerCheck.setOnClickListener{
                checkedAnswers.add(secondAnswerCheck.text.toString())
            }
            thirdAnswerCheck.setOnClickListener{
                checkedAnswers.add(thirdAnswerCheck.text.toString())
            }
            fourthAnswerCheck.setOnClickListener{
                checkedAnswers.add(fourthAnswerCheck.text.toString())
            }
            val textAnswer = textInput.text.toString()
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if(currentQuestion.type == "choice")
                {
                    if (answers[answerIndex] == currentQuestion.answers[0]) {
                        correctAnswers++
                    }
                }
                else if(currentQuestion.type == "text"){
                    if(textAnswer in currentQuestion.answers)
                    {
                        correctAnswers++
                    }
                }
                else if(currentQuestion.type == "multiple"){
                    if(checkedAnswers.size == 2 && (currentQuestion.answers[0] in checkedAnswers) && (currentQuestion.answers[1] in checkedAnswers))
                    {
                        correctAnswers++
                    }
                }
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = easyquestions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    }
                    if(questionIndex == numQuestions ){
                        view.findNavController().navigate(GameFragmentDirections
                            .actionGameFragmentToEndFragment(correctAnswers, numQuestions, joker))
                    }


            }
            setLayout()

        }
        binding.jokerButton.setOnClickListener(){
            jokerText.visibility = INVISIBLE
            jokerButton.visibility = INVISIBLE
            joker = true
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                if(currentQuestion.type=="choice" || currentQuestion.type == "multiple")
                {
                    putExtra(Intent.EXTRA_TEXT, "Help me answer this question: " + currentQuestion.text + currentQuestion.code + "Choices " +  currentQuestion.answers)
                }
                else{
                    putExtra(Intent.EXTRA_TEXT, "Help me answer this question: " + currentQuestion.text)
                }
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setLayout()
        super.onViewCreated(view, savedInstanceState)
    }



    private fun setLayout(){
        textInput.visibility = INVISIBLE
        textInputLayout.visibility = INVISIBLE
        questionRadioGroup.visibility = INVISIBLE
        checkBoxGroup.visibility = INVISIBLE
        textInput.text?.clear()
        code.visibility = INVISIBLE
        textInput.text?.clear()
        if(currentQuestion.type=="text"){
            textInput.visibility = VISIBLE
            textInputLayout.visibility = VISIBLE

        }
        if (currentQuestion.type=="choice"){

            questionRadioGroup.visibility = VISIBLE
        }
        if(currentQuestion.type=="multiple"){
            checkBoxGroup.visibility = VISIBLE
        }
        if(currentQuestion.code.isNotBlank()){
            code.visibility = VISIBLE
        }

    }

    // miješa liste pitanja i postavi questionIndex na nulu
    private fun randomizeQuestions() {
        easyquestions.shuffle()
        hardquestions.shuffle()
        mediumquestions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Postavlja pitanja i randomizuje odgovore.  Mijenja podatke, ne UI.
    private fun setQuestion() {
        if(difficulty == 1)
        {
            if(questionIndex<numQuestions/2)
            {
                currentQuestion = easyquestions[questionIndex]
            }
            else if(questionIndex<numQuestions/2+1 && questionIndex>=numQuestions/2-1){
                currentQuestion = mediumquestions[questionIndex]
            }
            else if(questionIndex>=numQuestions/2+1 && questionIndex<numQuestions){
                currentQuestion = hardquestions[questionIndex]
            }
        }
        else if(difficulty == 2 ){
            if(questionIndex<numQuestions/2)
            {
                currentQuestion = mediumquestions[questionIndex]
            }
            else if(questionIndex<numQuestions/2+1 && questionIndex>=numQuestions/2-1){
                currentQuestion = easyquestions[questionIndex]
            }
            else if(questionIndex>=numQuestions/2+1 && questionIndex<numQuestions){
                currentQuestion = hardquestions[questionIndex]
            }
        }
        else if(difficulty == 3){
            if(questionIndex<numQuestions/2)
            {
                currentQuestion = hardquestions[questionIndex]
            }
            else if(questionIndex<numQuestions/2+1 && questionIndex>=numQuestions/2){
                currentQuestion = easyquestions[questionIndex]
            }
            else if(questionIndex>=numQuestions/2+1 && questionIndex<numQuestions){
                currentQuestion = mediumquestions[questionIndex]
            }
        }

        // randomizira odgovore u kopiju liste
        answers = currentQuestion.answers.toMutableList()
        // i promiješa ih
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.questTitle, questionIndex + 1, numQuestions)
    }


}

