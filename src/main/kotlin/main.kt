import models.Item
import models.longTodo
import java.util.*

val todos  = mutableListOf<Item>()

fun main() {
    startApp()
}

fun startApp(){
    println("Hello, Welcome to TodoList")
    println("1. Enter app")
    println("99. Exit app")
    try {
        when(readLine()!!.toInt()){
            1 -> enterApp()
            99 -> exitApp()
            else -> invalidSelection { startApp() }
        }
    }catch (e : Exception){
        invalidSelection { startApp() }
    }
}

fun invalidSelection(stage : () -> Unit){
    println("Invalid selection, try again")
    stage()
}

fun enterApp(){
    println("1. List todos")
    println("2. Add todo")
    println("3. Edit todo")
    println("4. Delete todo")
    println("99. Exit app")
    try {
        when(readLine()!!.toInt()){
            1 -> listTodos()
            2 -> addTodo()
            3 -> editTodo()
            4 -> deleteTodo()
            99 -> exitApp()
            else -> invalidSelection { startApp() }
        }
    }catch (e : Exception){
        invalidSelection { enterApp() }
    }
}

fun listTodos(){
    if(todos.isEmpty()){
        println("You have no todos")
    } else {
        for ((index,todo) in todos.withIndex()){
            println("#${index+1} ${todo.title}")
            if (todo.longTodo()) println("long todo, consider shortening")
        }
        println("-------------------")
    }
    enterApp()
}

fun addTodo(){
    try {
        println("Enter your todo")
        val newTodoStr = readLine()
        if(newTodoStr!!.isEmpty()){
            invalidSelection { addTodo() }
        } else {
            val newTodo = Item(id = generateID(),title = newTodoStr.trim())
            todos.add(newTodo)
            println("Added")
            enterApp()
        }
    } catch (e : Exception){
        invalidSelection { addTodo() }
    }
}

fun editTodo(){
    try {
        if(todos.isEmpty()){
            println("You have no todos")
        } else {
            for ((index,todo) in todos.withIndex()){
                println("#${index+1} ${todo.title}")
            }
            println("-------------------")
            println("Enter the number you want to edit")
            val choice = readLine()!!.toInt()
            println("Enter new todo")
            val newTodoStr = readLine()
            todos[choice - 1].title = newTodoStr!!
            println("Edited")
        }
        enterApp()
    } catch (e : Exception){
        invalidSelection { editTodo() }
    }

}

fun deleteTodo(){
    try {
        if(todos.isEmpty()){
            println("You have no todos")
        } else {
            for ((index,todo) in todos.withIndex()){
                println("#${index+1} ${todo.title}")
            }
            println("-------------------")
            println("Enter the number you want to edit")
            val choice = readLine()!!.toInt()
            todos.removeAt(choice - 1)
            println("Deleted")
        }
        enterApp()
    } catch (e : Exception){
        invalidSelection { editTodo() }
    }
}

fun exitApp(){
    println("Good bye!!!")
}

fun generateID() : String = Date().time.toString()

