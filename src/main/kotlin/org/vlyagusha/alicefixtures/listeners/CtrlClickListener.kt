package org.vlyagusha.alicefixtures.listeners

import com.intellij.openapi.editor.event.EditorMouseEvent
import com.intellij.openapi.editor.event.EditorMouseListener
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiDocumentManager
import org.jetbrains.yaml.YAMLLanguage

class CtrlClickListener : EditorMouseListener {
    override fun mouseClicked(event: EditorMouseEvent) {
        // Проверяем, что нажат Ctrl (Win/Linux) или Cmd (macOS)
        val mouseEvent = event.mouseEvent
        if (!mouseEvent.isControlDown && !mouseEvent.isMetaDown) {
            return
        }

        val editor = event.editor
        val project = editor.project ?: return
        val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document) ?: return

        if (psiFile.language != YAMLLanguage.INSTANCE) return

        val offset = editor.caretModel.offset
        val lineNumber = editor.document.getLineNumber(offset)
        val lineText = editor.document.text.lines()[lineNumber]

        Messages.showInfoMessage("Строка $lineNumber: $lineText", "YAML Click")
    }
}