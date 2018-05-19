import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule, CanActivate } from '@angular/router';
import { RootComponent } from "./components/root/root.component";
import { ProcesarTextoComponent } from './components/procesartexto/procesartexto.component';
import { ProcesarArchivoComponent } from './components/procesararchivo/procesararchivo.component';

const appRoutes: Routes = [
    {
        path: '',
        component: RootComponent,
        children: [
            {
                path: '',
                component: ProcesarTextoComponent
            },
            {
                path: 'archivo',
                component: ProcesarArchivoComponent
            }
        ]
    }
];

export const appRoutingProviders: any[] = [];
export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);