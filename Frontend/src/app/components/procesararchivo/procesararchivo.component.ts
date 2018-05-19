import { Component } from "@angular/core";
import { TaggerService } from "../../services/tagger.service";
import { NERService } from "../../services/ner.service";
import { Oracion } from "../../models/oracion";
import { Entidad } from "../../models/entidad";

@Component({
    selector: 'procesararchivo',
    templateUrl: 'procesararchivo.component.html'
})
export class ProcesarArchivoComponent {

    public oraciones: Oracion[] = [];
    public entidades: Entidad[] = [];

    public constructor(
        private taggerService: TaggerService,
        private nerService: NERService
    ) {
        
    }

    public enviar(): void {
        
    }

}