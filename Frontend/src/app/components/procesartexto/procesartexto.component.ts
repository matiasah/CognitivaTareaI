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

    public constructor(
        private taggerService: TaggerService,
        private nerService: NERService
    ) {
        
    }

    public enviar(): void {

        // Obtener el contenido del textarea
        let contenido: string = this.textarea.nativeElement.value;

        // Si hay contenido
        if ( contenido.length > 0 ) {

            // Crear un objeto texto
            let texto: Texto = new Texto();

            // El objetot texto debe contener el contenido
            texto.contenido = contenido;

            // Solicitar los tags del texto
            this.taggerService.taggerize(texto).subscribe(
                Response => {
                    this.oraciones = Response;
                }
            );

            // Solicitar las entidades del texto
            this.nerService.list(texto).subscribe(
                Response => {
                    this.entidades = Response;
                }
            );

        }

    }

}