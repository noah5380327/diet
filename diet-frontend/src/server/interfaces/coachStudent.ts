export interface CoachStudent {
  id: string;
  studentId: string;
  coachId: string;
  status: string;
  createdTime: string;
  updatedTime: string;
}

export interface CoachStudentCreatePayload {
  coachId: string;
}

export interface CoachStudentUpdatePayload extends CoachStudentCreatePayload {}
