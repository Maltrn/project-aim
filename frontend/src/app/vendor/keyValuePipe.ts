import {PipeTransform, Pipe} from "@angular/core";

@Pipe({
  name: 'key'
})
export class KeyPipe implements PipeTransform {
  transform(obj: Object): string {
    return Object.getOwnPropertyNames(obj)[0];
  }
}

@Pipe({
  name: 'value'
})
export class ValuePipe implements PipeTransform {
  transform(obj: Object): string[] {
    return obj[Object.getOwnPropertyNames(obj)[0]];
  }
}
