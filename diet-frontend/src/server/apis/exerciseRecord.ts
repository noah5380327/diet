import axios from 'src/utils/axios';
import {
  ExerciseRecord,
  ExerciseRecordCreatePayload,
  ExerciseRecordUpdatePayload,
} from 'src/server/interfaces/exerciseRecord';

export function getExerciseRecords(): Promise<ExerciseRecord[]> {
  return axios({
    url: '/exerciseRecords',
  });
}

export function getExerciseRecordById(id: string): Promise<ExerciseRecord> {
  return axios({
    url: `/exerciseRecords/${id}`,
  });
}

export function createExerciseRecord(
  payload: ExerciseRecordCreatePayload
): Promise<void> {
  return axios({
    method: 'post',
    url: '/exerciseRecords',
    data: payload,
  });
}

export function updateExerciseRecordById(
  id: string,
  payload: ExerciseRecordUpdatePayload
): Promise<void> {
  return axios({
    method: 'put',
    url: `/exerciseRecords/${id}`,
    data: payload,
  });
}

export function deleteExerciseRecordById(id: string): Promise<void> {
  return axios({
    method: 'delete',
    url: `/exerciseRecords/${id}`,
  });
}
