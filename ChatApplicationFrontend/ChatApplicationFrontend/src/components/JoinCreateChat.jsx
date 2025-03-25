import React, { useState } from 'react';
import chatIcon from '../assets/speak.png';
import toast from "react-hot-toast"
import { createRoom as createRoomAPI, joinChatApi } from '../services/RoomService';
import useChatContext from '../context/chatContext';
import { useNavigate } from 'react-router';
const JoinCreateChat = () => {

   const [detail, setDetail] = useState({
    roomId:'',
    userName:''
   })

   const { roomId, userName, setRoomId, setCurrentUser, setConnected } = useChatContext();
   
   const navigate = useNavigate();

   function handleFormInputChange(event){
      setDetail({
        ...detail,
        [event.target.name]: event.target.value,
      })
   }

   function validateForm(){
      if(detail.roomId=="" || detail.userName==""){
        toast.error("Invalid Input..!")
        return false;
      }
      return true;
   }

   async function joinChat(){
      if(validateForm()){
        //  Call API For Join Room
        try {
          const room = await joinChatApi(detail.roomId);
          toast.success("joined successfully....");
          setCurrentUser(detail.userName);
          setRoomId(room.roomId);
          setConnected(true);
          navigate('/chat')
        } catch (error) {
          if(error.response && error.response.status == 400){
            toast.error("Room Not Exist...")
          }else{
            toast.error("Room Not Joined...");
          }
        }         
      }
   }

async function createRoom() {
  if (validateForm()) {
      try {
          const response = await createRoomAPI({ roomId: detail.roomId }); // Send JSON object
          console.log(response);
          toast.success("Room Created Successfully...");
          setCurrentUser(detail.userName);
          setRoomId(response.roomId);
          setConnected(true);
          navigate('/chat')
          // joinChat();
      } catch (error) {
          if(error.response && error.response.status == 400){
            toast.error("Room Already Exist...")
          }else{
            toast.error("Room Not Created...");
          }

      }
  }
}

  return (
    <div className="min-h-screen flex items-center justify-center ">
      <div className="p-10 dark:border-gray-700 border w-full flex flex-col gap-5 max-w-md rounded dark:bg-gray-900 shadow">
        <div>
          { <img src={chatIcon} className="w-24 mx-auto" alt="Chat Icon" /> }
        </div>

        <h1 className="text-2xl font-semibold text-center ">
          Join Room / Create Room ..
        </h1>

        {/* Name Input */}
        <div>
          <label htmlFor="name" className="block font-medium mb-2">
            Your name
          </label>
          <input
            onChange={handleFormInputChange}
            value={detail.userName}
            type="text"
            id="name"
            name="userName"
            placeholder="Enter the name"
            className="w-full dark:bg-gray-600 px-4 py-2 border dark:border-gray-600 rounded-full focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>

        {/* Room ID Input */}
        <div>
          <label htmlFor="roomId" className="block font-medium mb-2">
            Room ID / New Room ID
          </label>
          <input
              onChange={handleFormInputChange} 
              value={detail.roomId}
              type="text"
              id="roomId"
              name="roomId"
              placeholder="Enter the room id"
              className="w-full dark:bg-gray-600 px-4 py-2 border dark:border-gray-600 rounded-full focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
        </div>

        {/* Buttons */}
        <div className="flex justify-center gap-5 mt-4">
          <button onClick={joinChat} className="px-3 py-2 dark:bg-blue-500 hover:dark:bg-blue-800 rounded-full hover:cursor-pointer">
            Join Room
          </button>
          <button onClick={createRoom} className="px-3 py-2 dark:bg-orange-500 hover:dark:bg-orange-800 rounded-full hover:cursor-pointer">
            Create Room
          </button>
        </div>
      </div>
    </div>
  );
};

export default JoinCreateChat;
