package com.songapp.presentation.main

import com.songapp.fixedCapture
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.Mockito.`when` as whenDo

class MainPresenterTest {

    @get:Rule val rule: MockitoRule = MockitoJUnit.rule()

    @InjectMocks private lateinit var presenter: MainPresenter
    @Mock private lateinit var view: MainContract.View
    @Mock private lateinit var model: MainModel
    @Captor lateinit var argumentCaptor: ArgumentCaptor<BooleanArray>

    @Test
    fun shouldDisplaySourceDialogOnSettingsClicked() {
        //given
        whenDo(model.isLocalDataTurnedOn).thenReturn(true)
        whenDo(model.isRemoteDataTurnedOn).thenReturn(false)

        //when
        presenter.settingsClicked()

        //then
        verify(view).displayChooseSourceDialog(argumentCaptor.fixedCapture())
        assert(argumentCaptor.value[0])
        assert(!argumentCaptor.value[1])
    }
}