/**
 * Created by Maltron on 31.10.2016.
 */
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app.module';

const platform = platformBrowserDynamic()

platform.bootstrapModule(AppModule);
