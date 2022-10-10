import java.lang.Math.ceil

//EJERCICIO 1
fun calculadora():MutableList<Double>{
    println("Ingresa un valor: ")
    var n = readLine()!!.toDouble()

    var tam = ceil(n).toInt()
    var lista: MutableList<Double> = mutableListOf()

    println("Escoge la funcion a aplicar: ")
    println("1.-Seno\n2.-Coseno\n3.-Tangente\n4.-Exponencial\n5.-Logaritmo neperiano")
    var op = readLine()!!.toInt()
    var valor = 1.0
    when(op){
        1->{
            for (i in 1..tam){
                lista.add(Math.sin(i.toDouble()))
            }
        }
        2->{
            for (i in 1..tam){
                lista.add(Math.cos(i.toDouble()))
            }
        }
        3->{
            for (i in 1..tam){
                lista.add(Math.tan(i.toDouble()))
            }
        }
        4->{
            for (i in 1..tam){
                lista.add(Math.exp(i.toDouble()))
            }
        }
        5->{
            for (i in 1..tam){
                lista.add(Math.log(i.toDouble()))
            }
        }
    }

    return lista
}

fun imprimirTabla(res: List<Double>){
    var indice = 1
    for(i in 0..res.size-1){
        if(res[i].toString().length>16){
            print("| "+res[i].toString().subSequence(0,16)+" |\t")
        }else{
            print("| "+res[i]+" |\t")
        }

        if(indice%3==0){
            print("\n")
        }
        indice++
    }
    println()

}


//EJERCICIO 2
fun convertir(lista:List<Int>, discriminador:(Int)-> Boolean):List<Int>{
    var nlista : MutableList<Int> = mutableListOf()
    for(it in lista){
        if(discriminador(it)){
            nlista.add(it)
        }
    }
    return nlista
}

fun discriminador(n:Int):Boolean{
    return n%2==0
}

fun<T> imprimirLista(lista: List<T>){
    for(it in lista){
        print(""+it+" ")
    }
    println()
}

//EJERCICIO 3

fun calificaciones(lista: List<Int>):List<String>{
    var nlista : MutableList<String> = mutableListOf()
    for(it in lista){
        when(it){
            in 95..100 ->{
                nlista.add("[Excelente]")
            }
            in 85..94 ->{
                nlista.add("[Notable]")
            }
            in 75..84 ->{
                nlista.add("[Bueno]")
            }
            in 70..74->{
                nlista.add("[Suficiente]")
            }
            in 0..69->{
                nlista.add("[Desempeño insuficiente]")
            }
        }
    }
    return nlista
}

//EJERCICIO 4
fun asignaturas(mapa:HashMap<String,Int>): HashMap<String,String>{
    val mapa2 : HashMap<String,String> = HashMap()
    for(it in mapa){
        if(it.value>=95 && it.value<=100){
            mapa2.put(it.key.uppercase(),"Excelente")
        }else if(it.value>=85 && it.value<=94){
            mapa2.put(it.key.uppercase(),"Notable")
        }else if(it.value>=75 && it.value<=84){
            mapa2.put(it.key.uppercase(),"Bueno")
        }else if(it.value>=70 && it.value<=74){
            mapa2.put(it.key.uppercase(),"Suficiente")
        }else if(it.value<70){
            mapa2.put(it.key.uppercase(),"Desempeño insuficiente")
        }
    }
    return mapa2
}

fun imprimirDiccionario(mapa: HashMap<String,String>){
    for(it in mapa){
        println(""+it.key+" "+it.value)
    }
}

//EJERCICIO 5
fun busqueda(lista: List<Data>, presupuesto: Double):MutableMap<Data,Double>{
    var precio = 0.0
    var mapa : MutableMap<Data,Double> = hashMapOf()
    for(it in lista){
            var g = 0.0
            if(it.garaje==true){
                 g = 1.0
            }else{
                 g = 0.0
            }
            precio = (it.metros.toDouble()*1000.0+it.habitaciones.toDouble()*5000.0+g.toDouble()*15000.0)*(1.0-(2022.0-it.year.toDouble())/100.0)
            if(it.zona=='B'){
                precio*=1.5
            }
            if(precio<=presupuesto){
                mapa.put(it,precio)
            }
    }
    return mapa
}


fun main(args: Array<String>) {

    println("Funcion Ejercicio 1")
    imprimirTabla(calculadora())

    println("\nFuncion Ejercicio 2")
    imprimirLista(convertir(listOf(1,2,3,4,5,6,7,8,9,10),::discriminador))

    println("\nFuncion Ejercicio 3")
    imprimirLista(calificaciones(listOf(80,86,70,60,100,95,90,74,78)))

    print("\nFuncion Ejercicio 4\n")
    var resultado = asignaturas(hashMapOf("matematicas" to 95, "Español" to 89, "Programacion" to 100, "quIMICA" to 75, "historia" to 60, "Contabilidad" to 70 ))
    imprimirDiccionario(resultado)

    print("\nFuncion Ejercicio 5\n")
    var lista : MutableList<Data> = mutableListOf()
    lista.add(Data(2000,100,3,true,'A'))
    lista.add(Data(2012,60,2,true,'B'))
    lista.add(Data(1980,120,4,false,'A'))
    lista.add(Data(2005,75,3,true,'B'))
    lista.add(Data(2015,90,2,false,'A'))

    var pisos = busqueda(lista,95000.0)
    for(it in pisos){
        println("Año: "+it.key.year+" Metros: "+it.key.metros+" Habitaciones: "+it.key.habitaciones+" Garaje: "+it.key.garaje+" Zonas: "+it.key.zona+" Precio: "+it.value)

    }
}
data class Data(
    val year: Int,
    val metros: Int,
    val habitaciones: Int,
    val garaje: Boolean,
    val zona: Char,
    )