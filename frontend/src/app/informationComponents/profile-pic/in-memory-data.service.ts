/**
 * Created by Maltron on 07.11.2016.
 */
import {InMemoryDbService} from 'angular-in-memory-web-api';
export class InMemoryDataService implements InMemoryDbService
{
    createDb()
    {
        let file = ["a4564", "a4565", "a4566", "a4567"];
        return {file};
    }
}
