package com.example.cardgame

import com.example.cardgame.enums.Estado

class Carta {

 var id: Int
 var estado : Estado
 var codImgDestapada : Int
 var codImgTapada : Int


 constructor(id:Int, estado: Estado, codImgDestapada : Int, codImgTapada : Int){
     this.id = id
     this.estado = estado
     this.codImgDestapada = codImgDestapada
     this.codImgTapada = codImgTapada
 }

    //Mi objeto carta usa dos imagenes
    // codImgDestapada es la imagen de la categoria
    // codImgTapada es la imagen de la carta por detras, es decir oculta

    // Destapamos o Tapamos
    // Este metodo lo que hace es cambiar el lugar, la imagen de detras pasa a delante y la de delante a atras
    fun destapar(){
        var imagen = this.codImgDestapada
        this.codImgDestapada = this.codImgTapada
        this.codImgTapada = imagen
    }


}

