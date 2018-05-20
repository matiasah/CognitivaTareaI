import { Component, ElementRef, ViewChild } from "@angular/core";
import { TaggerService } from "../../services/tagger.service";
import { NERService } from "../../services/ner.service";
import { Oracion } from "../../models/oracion";
import { Entidad } from "../../models/entidad";
import { Palabra } from "../../models/palabra";
import { Texto } from "../../models/texto";

@Component({
    selector: 'procesartexto',
    templateUrl: 'procesartexto.component.html'
})
export class ProcesarTextoComponent {

    @ViewChild("textarea")
    public textarea: ElementRef;

    public oraciones: Oracion[] = [];
    public entidades: Entidad[] = [];

    public sustantivos: Palabra[] = [];
    public verbos: Palabra[] = [];

    public constructor(
        private taggerService: TaggerService,
        private nerService: NERService
    ) {

    }

    private ordenarPalabras(palabras: Palabra[], map: number[]) {
        for (let i = 0; i < palabras.length; i++) {

            let palabra: Palabra = palabras[i];
            let k: number = i;

            for (let j = i; j < palabras.length; j++) {

                let palabraSwap: Palabra = palabras[j];

                if (map[palabraSwap.palabra] > map[palabra.palabra]) {

                    palabra = palabraSwap;
                    k = j;

                }

            }

            let aux: Palabra = palabras[i];

            palabras[i] = palabra;
            palabras[k] = aux;

        }
    }

    public enviar(): void {

        // Obtener el contenido del textarea
        let contenido: string = this.textarea.nativeElement.value;

        // Si hay contenido
        if (contenido.length > 0) {

            // Crear un objeto texto
            let texto: Texto = new Texto();

            // El objetot texto debe contener el contenido
            texto.contenido = contenido;

            // Solicitar los tags del texto
            this.taggerService.taggerize(texto).subscribe(
                Response => {
                    // Guardar oraciones
                    this.oraciones = Response;

                    // Crear una lista de palabras
                    let listaPalabras: Palabra[] = [];
                    let listaSustantivos: Palabra[] = [];
                    let listaVerbos: Palabra[] = [];
                    let mapPalabras: number[] = [];

                    for (let i = 0; i < this.oraciones.length; i++) {

                        // Por cada oración
                        let oracion: Oracion = this.oraciones[i];
                        let palabras: Palabra[] = oracion.palabras;

                        for (let j = 0; j < palabras.length; j++) {

                            // Por cada palabra de la oración
                            let palabra = palabras[j];

                            if (!mapPalabras[palabra.palabra]) {
                                // Si la palabra no se ha mencionado anteriormente

                                if (palabra.tag == "NN" || palabra.tag == "NNP" || palabra.tag == "NNS") {
                                    // Si la palabra es un sustantivo

                                    // Agregar a la lista de sustantivos
                                    listaSustantivos[listaSustantivos.length] = palabra;

                                } else if (palabra.tag == "VB" || palabra.tag == "VBZ" || palabra.tag == "VBS") {
                                    // Si la palabra es un verbo

                                    // Agregar a la lista de verbos
                                    listaVerbos[listaVerbos.length] = palabra;
                                }

                                // Inicializar el contador de menciones en cero
                                mapPalabras[palabra.palabra] = 0;

                                // Agregar a la lista de palabras
                                listaPalabras[listaPalabras.length] = palabra;

                            }

                            mapPalabras[palabra.palabra] = mapPalabras[palabra.palabra] + 1;

                        }

                    }

                    // Ordenar lista de sustantivos (ordenamiento normal)
                    this.ordenarPalabras(listaSustantivos, mapPalabras);

                    // Ordenar lista de verbos (ordenamiento normal)
                    this.ordenarPalabras(listaVerbos, mapPalabras);

                    // Pasar datos a la vista
                    this.sustantivos = [];
                    this.verbos = [];

                    for (let i = 0; i < 10; i++) {
                        if (listaSustantivos[i]) {
                            this.sustantivos[i] = listaSustantivos[i];
                        }
                        if (listaVerbos[i]) {
                            this.verbos[i] = listaVerbos[i];
                        }
                    }

                }
            );

            // Solicitar las entidades del texto
            this.nerService.list(texto).subscribe(
                Response => {
                    // Guardar entidades
                    this.entidades = Response;
                }
            );

        }

    }

}