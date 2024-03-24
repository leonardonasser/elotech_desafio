import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import axios from 'axios';

export interface Person {
  id: number;
}

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor() { }

  public get(personId: number) {
    return axios.get(environment.apiUrl + `persons/${personId}`)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        console.error('Erro ao obter dados do backend:', error);
        throw error;
      });
  }
  
  public list() {
    return axios.get(environment.apiUrl + 'persons')
      .then(response => {
        return response.data;
      })
      .catch(error => {
        console.error('Erro ao obter dados do backend:', error);
        throw error;
      });
  }

  public create(params: any) {
    return axios.post(environment.apiUrl + 'persons', params)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        console.error('Erro ao obter dados do backend:', error);
        throw error;
      });
  }

  public update(personId, params: any) {
    return axios.put(environment.apiUrl + `persons/${personId}`, params)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        console.error('Erro ao obter dados do backend:', error);
        throw error;
      });
  }

  public delete(personId) {
    return axios.delete(environment.apiUrl + `persons/${personId}`)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        console.error('Erro ao obter dados do backend:', error);
        throw error;
      });
  }
}
