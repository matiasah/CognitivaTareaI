import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { GLOBAL } from "./global";
import { Texto } from "../models/texto";
import { Entidad } from "../models/entidad";

@Injectable({
    providedIn: 'root'
})
export class NERService {

    public constructor(
        private http: HttpClient
    ) {

    }

    public list(texto: Texto): Observable<Entidad[]> {
        return this.http.post<Entidad[]>(GLOBAL.url + "ner", texto);
    }

    public listFile(file: File): Observable<Entidad[]> {
        
        let form: FormData = new FormData();

        form.append("file", file);

        return this.http.post<Entidad[]>(GLOBAL.url + "ner-file", form);
    }

}