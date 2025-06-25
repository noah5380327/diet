import axios from 'src/utils/axios';
import { Recipe } from 'src/server/interfaces/recipe';

export function generateRecipe(): Promise<Recipe> {
  return axios({
    method: 'post',
    url: '/recipes/generate',
  });
}

export function getLatestRecipe(): Promise<Recipe> {
  return axios({
    url: '/recipes/latest',
  });
}

export function getRecipeByStudentId(studentId: string): Promise<Recipe> {
  return axios({
    url: `/recipes/students/${studentId}`,
  });
}
