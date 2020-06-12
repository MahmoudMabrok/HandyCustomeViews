package tools.mahmoudmabrok.customeviews.util

import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.threeten.bp.DateTimeException

class DateTiemHelperTest {


    /**
     * by default fields are private, `exceptionRule` must be public
     * so we add `get:`
     */
    @get:Rule
    val exceptionRule = ExpectedException.none()

    @Test
    fun `get12HourTime() with afternoon time `() {
        // arrange
        val h = 13
        val m = 22
        val expected = "01:22 PM"

        // act
        val res = DateTiemHelper.get12HourTime(h , m)

        // assert
        assertEquals(expected , res)
    }

    @Test
    fun `get12HourTime() with before 12  time `() {
        // arrange
        val h = 9
        val m = 22
        val expected = "09:22 AM"

        // act
        val res = DateTiemHelper.get12HourTime(h , m)

        // assert
        assertEquals(expected , res)
    }

   @Test
    fun `get12HourTime() with wrong date expect exception `() {
        // arrange
        val h = -1
        val m = 22

       // expect an exception to be thrown
       exceptionRule.expect(DateTimeException::class.java)

        // act
        val res = DateTiemHelper.get12HourTime(h , m)

    }


    @Test
    fun `get24HourTime() with before 12 times `() {
        // arrange
        val h = 5
        val m = 22
        val start = DateTiemHelper.get12HourTime(h , m)
        val expected = "05:22"

        // act
        val res = DateTiemHelper.get24HourTime(start)

        // assert
        assertEquals(expected , res)
    }

    @Test
    fun `get24HourTime() with after 12 times `() {
        // arrange
        val start = "10:22 PM"
        val expected = "22:22"

        // act
        val res = DateTiemHelper.get24HourTime(start)

        // assert
        assertEquals(expected , res)
    }

    @Test
    fun `isAfter() check that time is After `() {
        // arrange
        val afterHour = 10
        val afterMinutes = 10

        val cuurentHour = 9
        val cuurentMinutes = 9


        // act
        val res = DateTiemHelper.isAfterOrEqual(afterHour,afterMinutes,cuurentHour,cuurentMinutes)

        // assert
        assertTrue( res)

    }

    @Test
    fun `isAfter() check that time is After  with same time `() {
        // arrange
        val afterHour = 10
        val afterMinutes = 10

        val cuurentHour = 10
        val cuurentMinutes = 10


        // act
        val res = DateTiemHelper.isAfterOrEqual(afterHour,afterMinutes,cuurentHour,cuurentMinutes)

        // assert
        assertEquals(true ,  res)

    }  @Test
    fun `isBeforeOrEqual() check that time  is Before `() {
        // arrange
        val afterHour = 8
        val afterMinutes = 8

        val cuurentHour = 9
        val cuurentMinutes = 9


        // act
        val res = DateTiemHelper.isBeforeOrEqual(afterHour,afterMinutes,cuurentHour,cuurentMinutes)

        // assert
        assertEquals(true ,  res)

    }

    @Test
    fun `isBeforeOrEqual() check that time is Before  with same time `() {
        // arrange
        val afterHour = 10
        val afterMinutes = 10

        val cuurentHour = 10
        val cuurentMinutes = 10


        // act
        val res = DateTiemHelper.isBeforeOrEqual(afterHour,afterMinutes,cuurentHour,cuurentMinutes)

        // assert
        assertEquals(true ,  res)

    }

    @Test
    fun `isBeforeOrEqual() check that time is Before  with after time `() {
        // arrange
        val afterHour = 11
        val afterMinutes = 10

        val cuurentHour = 10
        val cuurentMinutes = 10

        // act
        val res = DateTiemHelper.isBeforeOrEqual(afterHour,afterMinutes,cuurentHour,cuurentMinutes)

        // assert
        assertEquals(false ,  res)

    }



}