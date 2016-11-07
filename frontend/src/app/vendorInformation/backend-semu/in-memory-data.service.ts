/**
 * Created by Maltron on 07.11.2016.
 */
import { InMemoryDbService } from 'angular-in-memory-web-api';
export class InMemoryDataService implements InMemoryDbService {
    createDb() {
        let files = ["test.jpg"
        ];
        return {files};
    }
}
