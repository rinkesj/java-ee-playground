FROM ibmcom/websphere-traditional:latest
ENV ENABLE_BASIC_LOGGING=true
COPY --chown=was:root ./target/jee8test.war /work/app/
COPY --chown=was:root ./deploy.py /work/config/
RUN /work/configure.sh

