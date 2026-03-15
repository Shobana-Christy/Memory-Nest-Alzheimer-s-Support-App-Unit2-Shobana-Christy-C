import './App.css'
import Header from './components/layout/Header'
import Footer from './components/layout/Footer'
import { BrowserRouter, Routes, Route } from 'react-router'
import LoginPage from './components/pages/LoginPage'
import HomePage from './components/pages/HomePage'
import AboutUsPage from './components/pages/AboutUsPage'
import ContactUsPage from './components/pages/ContactUsPage'
import RemindersPage from './components/pages/reminders/RemindersPage'
import MemorySpotPage from './components/pages/memoryspot/MemorySpotPage'
import EngagementCenterPage from './components/pages/engagementcenter/EngagementCenterPage'
import { useEffect, useState } from 'react'
import { getLoggedInUser } from './components/common/dataCollection'
import NotRegistered from './components/pages/NotRegistered'

function App() {

  return (
    <BrowserRouter>
      <div id="body-container">
        <Header/>
        <Routes>
          <Route path='/' element={<LoginPage />} />
          <Route path='/not_registered' element={<NotRegistered />} />
          <Route path="/home" element={<HomePage />} />
          <Route path="/aboutus" element={<AboutUsPage />} />
          <Route path="/contactus" element={<ContactUsPage />} />
          <Route path='/reminders' element={<RemindersPage />} />
          <Route path='/memoryspot' element={<MemorySpotPage />} />
          <Route path='/engagementcenter' element={<EngagementCenterPage />} />
        </Routes>
        <Footer />

      </div>
    </BrowserRouter>
  )
}

export default App;
