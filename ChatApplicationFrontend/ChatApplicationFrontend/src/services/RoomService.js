import { httpClient } from "../config/AxiosHelper"

export const createRoom = async (roomDetail)=>{
    const response = await httpClient.post(`/api/v1/rooms/createRoomById`, roomDetail)
    return response.data;
}

export const joinChatApi = async(roomId)=>{
     const response = await httpClient.get(`/api/v1/rooms/getRoomById/${roomId}`);
     return response.data;
}

export const getMessages = async(roomId)=>{
    const response = await httpClient.get(`/api/v1/rooms/message/${roomId}`);
    return response.data;
}