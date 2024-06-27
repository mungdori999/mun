// axiosInstance.js
import axios from 'axios';

// Axios 인스턴스를 생성합니다.
const axiosInstance = axios.create();
// 요청 인터셉터를 추가합니다.
axiosInstance.interceptors.request.use(
    (config) => {
        // 요청이 보내지기 전에 작업 수행
        const accessToken = localStorage.getItem('access');

        if (accessToken) {
            config.headers.access = accessToken;
        }
        return config;
    },
    (error) => {

        return Promise.reject(error);
    }
);

// 응답 인터셉터를 추가합니다.
axiosInstance.interceptors.response.use(
    (response) => {

        return response;
    },
    async (error) => {  // 오류 처리 함수에서 async 사용
        const originalRequest = error.config;


        if (error.response && error.response.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true;
            try {
                // 토큰 재발급 요청
                const response = await axios.post('/api/reissue');
                // 새로운 토큰 저장
                const newAccessToken = response.headers.access;
                localStorage.setItem('access', newAccessToken);
                // 원래 요청의 헤더를 새로운 토큰으로 업데이트
                originalRequest.headers.access = newAccessToken;
                // 원래 요청을 재시도
                return axiosInstance(originalRequest);
            } catch (err) {
                return Promise.reject(err);
            }
        }

        // 그 외의 오류 처리
        return Promise.reject(error);
    }
);

export default axiosInstance;
