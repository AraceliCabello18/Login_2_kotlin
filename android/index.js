const express = require('express')
const mysql = require('mysql')
const bodyParse = require('body-parse')
const { error } = require('console')

const app = express()

app.use(bodyParse.json())

const puerto = 3000;

const conexion = mysql.createConnection({
  host: 'localhost',
  database: 'gatos',
  user: 'root',
  password: ''
})

conexion.connect(error =>{
  if(error)throw error
  console.log('Conexion Exitosa a la Base de Datos')
})

app.get('/',(req, res)=>{
  res.send('API')
})
app.get('/appObtenerUsuarios',(req, res)=>{
  const query = 'SELECT * FROM t_usuarios'
  conexion.query(query,(error, results)=>{
    if(error) return console.error(error.message)
    if(results.length > 0){
      res.json(results)
    }else{
      res.json('no hay registro')
    }
  })
})

app.post('/appInicioSesion',(req, res)=>{
  const correo = req.body.correo
  const contrasena = req.body.contrasena

  const query = 'SELECT*FROM t_usuario WHERE correo =? AND contrasena = ?';

  conexion.query(query[correo,contrasena],(error, results)=>{
    if(errro){
      console.error(error)
      return res.status(500).jeson({error: "Error del Servidor"})
      
      
    }
    if(results.length > 0){
      res.json({message: "Login Exitoso", usuarios:results[0]})
    }else{
      res.status(401).json({error: "Credenciales Invalidas"})
    }
  })

})

app.post('/appAgregarUsuario', (req, res) =>{
  const usuario = {
  nombre: req.body.nombre,
  apPaterno: req.body.apPaterno,
  apMaterno: req.body.apMaterno,
  edad: req.body.edad,
  genero: req.body.genero,
  correo: req.body.correo,
  contrasena: req.body.contrasena,
  fechaNacimiento: req.body.fechaNacimiento
  }
const querry = 'INSERT INTO t_usuario SET ?'
conexion.query(query, usuario, (error)=>{
  if(error) return console.error(error.message)

  res.json('Se inserto correctamente el usuario')
})
})
app.put('/appActualizarUsuario/:id', (req, res) =>{
  const {id} = req.params
  const{nombre, apPaterno, apMaterno, edad, genero, correo, contrasena, 
  fechaNacimiento} = req.body

  const query = `UPDATE t_usuario SET nombre='${nombre}', apPaterno='${apPaterno}', apMaterno='${apMaterno}', edad='${edad}',
  genero='${genero}', correo='${correo}', contrasena='${contrasena}', fechaNacimiento='${fechaNacimiento}' WHERE id_usuarios='${id}'`

  conexion.query(query,(error)=>{
    if(error) return console.error(error.message)

    res.json('Se Actualizo Correctamente el Usuario')
  })
})
app.delete('/appEliminarUsuario/:id', (req, res) =>{
const {id} = req.params

const query = `DELETE FROM t_usuario WHERE id_usuarios=${id}`

conexion.query(query, (error)=>{
  if(error) return console.error(error.message)

  res.json('Se Elimino Correctamente el Usuario')

})
})