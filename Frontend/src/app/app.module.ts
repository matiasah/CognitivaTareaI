import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import { routing, appRoutingProviders } from './app.routing';
import { RootComponent } from './components/root/root.component';
import { MaterializeModule } from 'angular2-materialize';
import { ProcesarTextoComponent } from './components/procesartexto/procesartexto.component';

@NgModule({
  declarations: [
    AppComponent,
    RootComponent,
    ProcesarTextoComponent
  ],
  imports: [
    BrowserModule,
    MaterializeModule,
    routing
  ],
  exports: [],
  providers: [
    appRoutingProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
