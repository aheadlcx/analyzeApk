package bolts;

public interface f<TTaskResult, TContinuationResult> {
    TContinuationResult then(g<TTaskResult> gVar) throws Exception;
}
