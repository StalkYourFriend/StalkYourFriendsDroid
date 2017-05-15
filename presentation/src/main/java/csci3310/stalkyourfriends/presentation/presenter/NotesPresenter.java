package csci3310.stalkyourfriends.presentation.presenter;

import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.domain.entity.VersionEntity;
import csci3310.stalkyourfriends.domain.interactor.CheckVersionExpirationUseCase;
import csci3310.stalkyourfriends.domain.interactor.note.GetNotesUseCase;
import csci3310.stalkyourfriends.presentation.dependency.ActivityScope;
import csci3310.stalkyourfriends.presentation.view.BaseView;
import csci3310.stalkyourfriends.presentation.view.NotesView;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class NotesPresenter extends BasePresenter implements Presenter {

    private GetNotesUseCase getNotesUseCase;
    private CheckVersionExpirationUseCase checkVersionExpirationUseCase;
    NotesView notesView;

    @Inject
    public NotesPresenter(GetNotesUseCase getNotesUseCase,
                          CheckVersionExpirationUseCase checkVersionExpirationUseCase) {
        super(getNotesUseCase, checkVersionExpirationUseCase);
        this.getNotesUseCase = getNotesUseCase;
        this.checkVersionExpirationUseCase = checkVersionExpirationUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.notesView = (NotesView) view;
        this.checkVersionExpirationUseCase.execute(new VersionExpirationSubscriber());
    }

    @Override
    public void resume() {
        this.showLoader();
        this.getNotesUseCase.execute(new NotesSubscriber());
    }

    @Override
    public void destroy() {
        super.destroy();
        this.notesView = null;
    }

    protected class NotesSubscriber extends BaseSubscriber<List<NoteEntity>> {

        @Override public void onNext(List<NoteEntity> notes) {
            NotesPresenter.this.hideLoader();
            NotesPresenter.this.notesView.showNotes(notes);
        }
    }

    protected class VersionExpirationSubscriber extends BaseSubscriber<VersionEntity> {

        @Override public void onNext(VersionEntity version) {
            NotesPresenter.this.hideLoader();
            if (version.isWarned()) NotesPresenter.this.notesView.showExpirationWarning();
        }
    }

}
