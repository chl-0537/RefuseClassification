package com.example.refuseclassification;

import java.util.List;

public class ASRresponse {

    /**
     * results_recognition : ["你好，"]
     * result_type : final_result
     * best_result : 你好，
     * origin_result : {"asr_align_begin":80,"asr_align_end":130,"corpus_no":6835867007181645805,"err_no":0,"raf":133,"result":{"word":["你好，"]},"sn":"82d975e0-6eb4-43ac-a0e7-850bb149f28e"}
     * error : 0
     */

    private String result_type;
    private String best_result;
    private OriginResultBean origin_result;
    private int error;
    private List<String> results_recognition;

    public String getResult_type() {
        return result_type;
    }

    public void setResult_type(String result_type) {
        this.result_type = result_type;
    }

    public String getBest_result() {
        return best_result;
    }

    public void setBest_result(String best_result) {
        this.best_result = best_result;
    }

    public OriginResultBean getOrigin_result() {
        return origin_result;
    }

    public void setOrigin_result(OriginResultBean origin_result) {
        this.origin_result = origin_result;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<String> getResults_recognition() {
        return results_recognition;
    }

    public void setResults_recognition(List<String> results_recognition) {
        this.results_recognition = results_recognition;
    }

    public static class OriginResultBean {
        /**
         * asr_align_begin : 80
         * asr_align_end : 130
         * corpus_no : 6835867007181645805
         * err_no : 0
         * raf : 133
         * result : {"word":["你好，"]}
         * sn : 82d975e0-6eb4-43ac-a0e7-850bb149f28e
         */

        private int asr_align_begin;
        private int asr_align_end;
        private long corpus_no;
        private int err_no;
        private int raf;
        private ResultBean result;
        private String sn;

        public int getAsr_align_begin() {
            return asr_align_begin;
        }

        public void setAsr_align_begin(int asr_align_begin) {
            this.asr_align_begin = asr_align_begin;
        }

        public int getAsr_align_end() {
            return asr_align_end;
        }

        public void setAsr_align_end(int asr_align_end) {
            this.asr_align_end = asr_align_end;
        }

        public long getCorpus_no() {
            return corpus_no;
        }

        public void setCorpus_no(long corpus_no) {
            this.corpus_no = corpus_no;
        }

        public int getErr_no() {
            return err_no;
        }

        public void setErr_no(int err_no) {
            this.err_no = err_no;
        }

        public int getRaf() {
            return raf;
        }

        public void setRaf(int raf) {
            this.raf = raf;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public static class ResultBean {
            private List<String> word;

            public List<String> getWord() {
                return word;
            }

            public void setWord(List<String> word) {
                this.word = word;
            }
        }
    }
}
