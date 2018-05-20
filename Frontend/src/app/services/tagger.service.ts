import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { GLOBAL } from "./global";
import { Texto } from "../models/texto";
import { Oracion } from "../models/oracion";

@Injectable({
    providedIn: 'root'
})
export class TaggerService {

    public constructor(
        private http: HttpClient
    ) {

    }

    public taggerize(texto: Texto): Observable<Oracion[]> {
        return this.http.post<Oracion[]>(GLOBAL.url + "tagger", texto);
    }

    public taggerizeFile(file: File): Observable<Oracion[]> {

        let form: FormData = new FormData();

        form.append("file", file);

        return this.http.post<Oracion[]>(GLOBAL.url + "tagger-file", form);
    }

}