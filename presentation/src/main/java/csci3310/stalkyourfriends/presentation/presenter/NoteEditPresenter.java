package csci3310.stalkyourfriends.presentation.presenter;

import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.domain.entity.VoidEntity;
import csci3310.stalkyourfriends.domain.interactor.note.DeleteNoteUseCase;
import csci3310.stalkyourfriends.domain.interactor.note.GetNoteUseCase;
import csci3310.stalkyourfriends.domain.interactor.note.UpdateNoteUseCase;
import csci3310.stalkyourfriends.presentation.dependency.ActivityScope;
import csci3310.stalkyourfriends.presentation.view.BaseView;
import csci3310.stalkyourfriends.presentation.view.NoteEditView;

import javax.inject.Inject;

@ActivityScope
public class NoteEditPresenter extends BasePresenter implements Presenter {

    private UpdateNoteUseCase updateNoteUseCase;
    private GetNoteUseCase getNoteUseCase;
    private DeleteNoteUseCase deleteNoteUseCase;
    NoteEditView noteEditView;

    @Inject
    public NoteEditPresenter(UpdateNoteUseCase updateNoteUseCase,
                             GetNoteUseCase getNoteUseCase, DeleteNoteUseCase deleteNoteUseCase) {
        super(updateNoteUseCase, getNoteUseCase, deleteNoteUseCase);
        this.updateNoteUseCase = updateNoteUseCase;
        this.getNoteUseCase = getNoteUseCase;
        this.deleteNoteUseCase = deleteNoteUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.noteEditView = (NoteEditView) view;

        this.showLoader();
        this.getNoteUseCase.setParams(this.noteEditView.getNoteId());
        this.getNoteUseCase.execute(new GetNoteSubscriber());
    }

    @Override
    public void destroy() {
        super.destroy();
        this.noteEditView = null;
    }

    protected class GetNoteSubscriber extends BaseSubscriber<NoteEntity> {

        @Override public void onNext(NoteEntity note) {
            NoteEditPresenter.this.hideLoader();
            NoteEditPresenter.this.noteEditView.showNote(note);
        }
    }

    public void updateNote(String title, String content) {
        NoteEntity updatedNote = new NoteEntity(title, content);
        updatedNote.setId(this.noteEditView.getNoteId());

        this.noteEditView.showLoader();
        this.updateNoteUseCase.setParams(updatedNote);
        this.updateNoteUseCase.execute(new UpdateNoteSubscriber());
    }

    protected class UpdateNoteSubscriber extends BaseSubscriber<NoteEntity> {

        @Override public void onNext(NoteEntity note) {
            NoteEditPresenter.this.hideLoader();
            NoteEditPresenter.this.noteEditView.close();
        }

    }

    public void deleteNoteButtonPressed(){
        this.noteEditView.showLoader();
        this.deleteNoteUseCase.setParams(this.noteEditView.getNoteId());
        this.deleteNoteUseCase.execute(new DeleteNoteSubscriber());
    }

    protected class DeleteNoteSubscriber extends BaseSubscriber<VoidEntity> {

        @Override public void onNext(VoidEntity ignore) {
            NoteEditPresenter.this.hideLoader();
            NoteEditPresenter.this.noteEditView.close();
        }

    }

}
