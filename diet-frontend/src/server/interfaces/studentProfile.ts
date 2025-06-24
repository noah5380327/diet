export interface StudentProfile {
  id: string;
  age: number;
  initWeight: number;
  targetWeight: number;
  trainingDuration: number;
  goal: string;
  userId: string;
  createdTime: string;
  updatedTime: string;
}

export interface StudentProfileCreatePayload {
  age: number;
  initWeight: number;
  targetWeight: number;
  trainingDuration: number;
  goal: string;
}

export interface StudentProfileUpdatePayload
  extends StudentProfileCreatePayload {}
