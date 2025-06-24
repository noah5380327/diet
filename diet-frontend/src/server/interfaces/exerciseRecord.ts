export interface ExerciseRecord {
  id: string;
  name: string;
  duration: number;
  calories: number;
  userId: string;
  createdTime: string;
  updatedTime: string;
}

export interface ExerciseRecordCreatePayload {
  name: string;
  duration: number;
  calories: number;
}

export interface ExerciseRecordUpdatePayload
  extends ExerciseRecordCreatePayload {}
