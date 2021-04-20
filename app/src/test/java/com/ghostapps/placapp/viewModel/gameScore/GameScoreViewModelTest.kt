package com.ghostapps.placapp.viewModel.gameScore

import com.ghostapps.placapp.domain.useCases.InsertRegister
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameScoreViewModelTest {

    private val gameScoreContractMock: GameScoreContract = mock {}
    private val insertRegisterMock: InsertRegister = mock {
        given { it.execute(any()) }.willReturn { true }
    }

    private lateinit var sut: GameScoreViewModel

    @Before
    fun setup() {
        sut = GameScoreViewModel(gameScoreContractMock, insertRegisterMock)
    }

    @Test
    fun `Should update team names correctly`() {
        val teamAName = "team_a"
        val teamBName = "team_b"

        sut.onCreate(teamAName, teamBName)

        assertEquals(sut.homeTeamName, teamAName)
        assertEquals(sut.awayTeamName, teamBName)
    }

    @Test
    fun `Should increase home team score when onHomeTeamIncrease is called`() {
        sut.onHomeTeamIncrease()
        assertEquals(sut.formattedHomeTeamScore, "01")

        sut.onHomeTeamIncrease()
        assertEquals(sut.formattedHomeTeamScore, "02")
    }

    @Test
    fun `Should decrease home team score when onHomeTeamDecrease is called`() {
        sut.formattedHomeTeamScore = "03"
        sut.onHomeTeamDecrease()
        assertEquals(sut.formattedHomeTeamScore, "02")

        sut.onHomeTeamDecrease()
        assertEquals(sut.formattedHomeTeamScore, "01")
    }

    @Test
    fun `Should increase away team score when onAwayTeamIncrease is called`() {
        sut.onAwayTeamIncrease()
        assertEquals(sut.formattedAwayTeamScore, "01")

        sut.onAwayTeamIncrease()
        assertEquals(sut.formattedAwayTeamScore, "02")
    }

    @Test
    fun `Should decrease away team score when onAwayTeamDecrease is called`() {
        sut.formattedAwayTeamScore = "03"
        sut.onAwayTeamDecrease()
        assertEquals(sut.formattedAwayTeamScore, "02")

        sut.onAwayTeamDecrease()
        assertEquals(sut.formattedAwayTeamScore, "01")
    }

    @Test
    fun `Should increase home team advantage point when updateScoreHomeAdvPoint is called`() {
        sut.updateScoreHomeAdvPoint()
        assertEquals(sut.formattedHomeTeamScoreAdvPoint, "01")

        sut.updateScoreHomeAdvPoint()
        assertEquals(sut.formattedHomeTeamScoreAdvPoint, "02")
    }

    @Test
    fun `Should increase away team advantage point when updateScoreAwayAdvPoint is called`() {
        sut.updateScoreAwayAdvPoint()
        assertEquals(sut.formattedAwayTeamScoreAdvPoint, "01")

        sut.updateScoreAwayAdvPoint()
        assertEquals(sut.formattedAwayTeamScoreAdvPoint, "02")
    }

    @Test
    fun `Should increase home team set when updateScoreAdvPoint is called`() {
        sut.formattedHomeTeamScoreAdvPoint = "06"
        sut.formattedAwayTeamScoreAdvPoint = "04"
        sut.updateScoreAdvPoint()
        assertEquals(sut.formattedHomeTeamScoreSet, "01")
    }

    @Test
    fun `Should increase away team set when updateScoreAdvPoint is called`() {
        sut.formattedAwayTeamScoreAdvPoint = "06"
        sut.formattedHomeTeamScoreAdvPoint = "04"
        sut.updateScoreAdvPoint()
        assertEquals(sut.formattedAwayTeamScoreSet, "01")
    }

    @Test
    fun `Should increase home team set when updateScoreSet is called`() {
        sut.formattedHomeTeamScore = "11"
        sut.formattedAwayTeamScore = "04"
        sut.updateScoreSet()
        assertEquals(sut.formattedHomeTeamScoreSet, "01")
    }

    @Test
    fun `Should increase away team set when updateScoreSet is called`() {
        sut.formattedHomeTeamScore = "06"
        sut.formattedAwayTeamScore = "11"
        sut.updateScoreSet()
        assertEquals(sut.formattedAwayTeamScoreSet, "01")
    }

//    @Test
//    fun `Should call onInsertRegisterFails when insertRegister fails`() {
//        val insertRegisterFailsMock: InsertRegister = mock {
//            given { it.execute(any()) }.willReturn { true }
//        }
//
//        sut = GameScoreViewModel(gameScoreContractMock, insertRegisterFailsMock)
//
//        sut.onExitPressed()
//
//        verify(gameScoreContractMock, times(1)).onInsertRegisterFails()
//    }





}