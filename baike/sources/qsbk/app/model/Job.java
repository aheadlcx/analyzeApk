package qsbk.app.model;

public class Job {
    private String a;

    public Job(String str) {
        this.a = str;
    }

    public String getName() {
        return this.a;
    }

    public int hashCode() {
        return this.a != null ? this.a.hashCode() : super.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof Job)) {
            Job job = (Job) obj;
            if (!(job.a == null || this.a == null)) {
                return job.a.equals(this.a);
            }
        }
        return false;
    }

    public String toString() {
        return "Job [name=" + this.a + "]";
    }
}
