package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var no : String = ""
    var nom : String =""
    var carr : String = ""
    var edad : String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buscarEstudiante(v:View){
        if (etControl.text.isEmpty()){
            Toast.makeText(this, "Ingresa el Numero de control", Toast.LENGTH_SHORT).show();
        }else{
             no = etControl.text.toString()
            val admin = adminBD(this)//Se crea una instancia de la base de datos
                                                        //0     //1     //2    //3
            val tupla = admin.Consulta("SELECT noControl,nomEst,carrera,edadEst FROM Estudiante WHERE noControl = '$no'")//Se manda a ejecutar el query
            if (tupla!!.moveToFirst()){
                etNombre.setText(tupla.getString(1))
                etCarrera.setText(tupla.getString(2))
                etEdad.setText(tupla.getString(3))
                btnAgregar.isEnabled =false
                btnModificar.isEnabled = true
                btnEliminar.isEnabled = true

            }
            else   {
                Toast.makeText(this, "No Existe El Numero de Control", Toast.LENGTH_SHORT).show();
                etControl.requestFocus()
            }

        }
    }
    fun agregarEstudiante(v:View){
        if (etControl.text.isEmpty()){
            Toast.makeText(this, "Falta El Numero De Control", Toast.LENGTH_SHORT).show();
            etControl.requestFocus()
        }else  if (etNombre.text.isEmpty()){
            Toast.makeText(this, "Falta El Nombre ", Toast.LENGTH_SHORT).show();
            etNombre.requestFocus()
        }else   if (etCarrera.text.isEmpty()){
            Toast.makeText(this, "Falta La Carrera", Toast.LENGTH_SHORT).show();
            etCarrera.requestFocus()
        }else if(etEdad.text.isEmpty()){
            Toast.makeText(this, "Falta La Edad", Toast.LENGTH_SHORT).show();
            etEdad.requestFocus()
        }else {
            leerCajas()
            var sentencia = "INSERT INTO Estudiante (noControl,nomEst,carrera,edadEst) VALUES ('$no','$nom','$carr',$edad)"
            val admin = adminBD(this)
            if (admin.Ejecuta(sentencia)==1){
                Toast.makeText(this, "Se Agrego Estudiante", Toast.LENGTH_SHORT).show();
                limpiarCajas()
            }else{
                Toast.makeText(this, "Estudiante NO agregado", Toast.LENGTH_SHORT).show();
                etControl.requestFocus()
            }
        }
    }
    fun modificarEstudiante(v:View){
        if (etControl.text.isEmpty()){
            Toast.makeText(this, "Falta El Numero De Control", Toast.LENGTH_SHORT).show();
            etControl.requestFocus()
        }else  if (etNombre.text.isEmpty()){
            Toast.makeText(this, "Falta El Nombre ", Toast.LENGTH_SHORT).show();
            etNombre.requestFocus()
        }else   if (etCarrera.text.isEmpty()){
            Toast.makeText(this, "Falta La Carrera", Toast.LENGTH_SHORT).show();
            etCarrera.requestFocus()
        }else if(etEdad.text.isEmpty()){
            Toast.makeText(this, "Falta La Edad", Toast.LENGTH_SHORT).show();
            etEdad.requestFocus()
        }else {
            leerCajas()
            var sentencia = "Update Estudiante SET nomEst='$nom',carrera='$carr',edadEst=$edad WHERE noControl = '$no'"
            val admin = adminBD(this)
            if (admin.Ejecuta(sentencia)==1){
                Toast.makeText(this, "Se Modifico Estudiante", Toast.LENGTH_SHORT).show();
                limpiarCajas()
            }else{
                Toast.makeText(this, "Estudiante NO se modifico", Toast.LENGTH_SHORT).show();
                etControl.requestFocus()
            }
        }
    }
    fun eliminarEstudiante(v:View){
        if (etControl.text.isEmpty()){
            Toast.makeText(this, "Ingresa el Numero de control", Toast.LENGTH_SHORT).show();
        }else{
            var sentencia = "DELETE FROM Estudiante WHERE noControl= '$no' "
            val admin = adminBD(this)
            if (admin.Ejecuta(sentencia)==1){
                Toast.makeText(this, "Se Elimino El Estudiante", Toast.LENGTH_SHORT).show();
                limpiarCajas()
            }else {
                Toast.makeText(this, "No se elimino Estudiante", Toast.LENGTH_SHORT).show();
            }
        }

    }
    fun leerCajas(){
        no = etControl.text.toString()
        nom = etNombre.text.toString()
        carr = etCarrera.text.toString()
        edad = etEdad.text.toString()
    }

    fun limpiarCajas(){
        no = ""
        nom = ""
        carr = ""
        edad ="0"
        etCarrera.setText("")
        etControl.setText("")
        etNombre.setText("")
        etEdad.setText("0")
        etControl.requestFocus()
        btnAgregar.isEnabled = true
        btnModificar.isEnabled = false
        btnEliminar.isEnabled = false
    }

}
