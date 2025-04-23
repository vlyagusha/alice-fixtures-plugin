package org.vlyagusha.alicefixtures

import com.intellij.openapi.components.BaseComponent
import com.intellij.openapi.editor.EditorFactory
import org.vlyagusha.alicefixtures.listeners.CtrlClickListener

class AliceFixturesPlugin : BaseComponent {
    override fun initComponent() {
        EditorFactory.getInstance().eventMulticaster.addEditorMouseListener(CtrlClickListener())
    }
}