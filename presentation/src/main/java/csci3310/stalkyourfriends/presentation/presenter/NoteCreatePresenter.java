package csci3310.stalkyourfriends.presentation.presenter;

import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.domain.interactor.note.CreateNoteUseCase;
import csci3310.stalkyourfriends.presentation.dependency.ActivityScope;
import csci3310.stalkyourfriends.presentation.view.BaseView;
import csci3310.stalkyourfriends.presentation.view.NoteCreateView;

import javax.inject.Inject;

@ActivityScope
public class NoteCreatePresenter extends BasePresenter implements Presenter {

    private CreateNoteUseCase createNoteUseCase;
    NoteCreateView noteCreateView;

    @Inject
    public NoteCreatePresenter(CreateNoteUseCase createNoteUseCase) {
        super(createNoteUseCase);
        this.createNoteUseCase = createNoteUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.noteCreateView = (NoteCreateView) view;
    }

    @Override
    public void destroy() {
        super.destroy();
        this.noteCreateView = null;
    }

    public void createButtonPressed(String title, String content) {
        NoteEntity note = new NoteEntity(title, content);

        this.noteCreateView.showLoader();
        this.createNoteUseCase.setParams(note);
        this.createNoteUseCase.execute(new NoteCreateSubscriber());
    }

    protected class NoteCreateSubscriber extends BaseSubscriber<NoteEntity> {

        @Override public void onNext(NoteEntity note) {
            NoteCreatePresenter.this.hideLoader();
            NoteCreatePresenter.this.noteCreateView.close();
        }
    }

}
