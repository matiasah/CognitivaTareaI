import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import { routing, appRoutingProviders } from './app.routing';
import { RootComponent } from './components/root/root.component';
import { MaterializeModule } from 'angular2-materialize';
import { ProcesarTextoComponent } from './components/procesartexto/procesartexto.component';
import { ProcesarArchivoComponent } from './components/procesararchivo/procesararchivo.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    RootComponent,
    ProcesarTextoComponent,
    ProcesarArchivoComponent
  ],
  imports: [
    BrowserModule,
    MaterializeModule,
    HttpClientModule,
    routing
  ],
  exports: [],
  providers: [
    appRoutingProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
