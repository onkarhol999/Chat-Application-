import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {BrowserRouter} from 'react-router'
import {Toaster} from 'react-hot-toast'
import './index.css'
 
import App from './App.jsx'
import AppRoutes from './config/AppRoutes.jsx'
import { ChatProvider } from './context/chatContext.jsx'

createRoot(document.getElementById('root')).render(
    <BrowserRouter>
        <ChatProvider>
          <AppRoutes/>
        </ChatProvider>
        <Toaster/>
    </BrowserRouter>
)
